package com.github.supercodingspring.service;

import com.github.supercodingspring.repository.items.ElectronicStoreItemRepository;
import com.github.supercodingspring.repository.items.ItemEntity;
import com.github.supercodingspring.repository.storeSales.StoreSales;
import com.github.supercodingspring.repository.storeSales.StoreSalesRepository;
import com.github.supercodingspring.service.exceptions.NotAcceptException;
import com.github.supercodingspring.service.exceptions.NotFoundException;
import com.github.supercodingspring.service.mapper.ItemMapper;
import com.github.supercodingspring.web.dto.items.BuyOrder;
import com.github.supercodingspring.web.dto.items.Item;
import com.github.supercodingspring.web.dto.items.ItemBody;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectronicStoreItemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ElectronicStoreItemRepository electronicStoreItemRepository;
    private final StoreSalesRepository storeSalesRepository;

    public List<Item> findAllItem() {
        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems();
        if (itemEntities.isEmpty()) throw new NotFoundException("아무 Items 들을 찾을 수 없습니다.");

        return itemEntities.stream().map(ItemMapper.INSTANCE::itemEntityToItem).collect(Collectors.toList());
    }

    public Integer savaItem(ItemBody itemBody) {
        ItemEntity itemEntity = ItemMapper.INSTANCE.idAndItemBodyToItemEntity(null, itemBody);
        try {
            electronicStoreItemRepository.saveItem(itemEntity);
        } catch (RuntimeException exception){
            throw new NotAcceptException("Item을 저장하는 도중에 Error 가 발생하였습니다.");
        }

        ItemEntity itemEntityCreated = electronicStoreItemRepository.findItemByName(itemEntity.getName())
                                                                    .orElseThrow(() -> new NotFoundException("해당 이름의 Item을 찾을 수 없습니다."));
        return itemEntityCreated.getId();
    }

    public Item findItemById(String id) {
        Integer idInt = Integer.parseInt(id);
        ItemEntity itemEntity = electronicStoreItemRepository.findItemById(idInt)
                                                             .orElseThrow(() -> new NotFoundException("해당 ID: " + idInt + "의 Item을 찾을 수 없습니다."));
        Item item = ItemMapper.INSTANCE.itemEntityToItem(itemEntity);
        return item;
    }

    public List<Item> findItemsByIds(List<String> ids) {
        List<ItemEntity> itemEntities = electronicStoreItemRepository.findAllItems();
        if (itemEntities.isEmpty()) throw new NotFoundException("아무 Items 들을 찾을 수 없습니다.");

        return itemEntities.stream()
                                       .map(ItemMapper.INSTANCE::itemEntityToItem)
                                       .filter((item -> ids.contains(item.getId())))
                                       .collect(Collectors.toList());
    }

    public void deleteItem(String id) {
        Integer idInt = Integer.parseInt(id);
        electronicStoreItemRepository.deleteItem(idInt);
    }

    public Item updateItem(String id, ItemBody itemBody) {
        Integer idInt = Integer.valueOf(id);
        ItemEntity itemEntity = ItemMapper.INSTANCE.idAndItemBodyToItemEntity(null, itemBody);
        ItemEntity itemEntityUpdated = electronicStoreItemRepository.updateItemEntity(idInt, itemEntity);

        return ItemMapper.INSTANCE.itemEntityToItem(itemEntityUpdated);
    }

    @Transactional(transactionManager = "tm1")
    public Integer buyItems(BuyOrder buyOrder) {
        // 1. BuyOrder 에서 상품 ID와 수량을 얻어낸다.
        // 2. 상품을 조회하여 수량이 얼마나 있는 지 확인한다.
        // 3. 상품의 수량과 가격을 가지고 계산하여 총 가격을 구한다.
        // 4. 상품의 재고에 기존 계산한 재고를 구매하는 수량을 뺸다.
        // 5. 상품 구매하는 수량 * 가격 만큼 가계 매상으로 올린다.
        // (단, 재고가 아예 없거나 매장을 찾을 수 없으면 살 수 없다. )

        Integer itemId = buyOrder.getItemId();
        Integer itemNums = buyOrder.getItemNums();

        ItemEntity itemEntity = electronicStoreItemRepository.findItemById(itemId)
                                                             .orElseThrow(() -> new NotFoundException("해당 이름의 Item을 찾을 수 없습니다."));

        if (itemEntity.getStoreId() == null ) throw new NotFoundException("매장을 찾을 수 없습니다.");
        if (itemEntity.getStock() <= 0) throw new NotAcceptException("상품의 재고가 없습니다.");

        Integer successBuyItemNums;
        if ( itemNums >= itemEntity.getStock() ) successBuyItemNums = itemEntity.getStock();
        else successBuyItemNums = itemNums;

        Integer totalPrice = successBuyItemNums * itemEntity.getPrice();

        // Item 재고 감소
        electronicStoreItemRepository.updateItemStock(itemId, itemEntity.getStock() - successBuyItemNums);

        if (successBuyItemNums == 4) {
            logger.error("4개를 구매하는 건 허락하지않습니다.");
            throw new NotAcceptException("4개를 구매하는건 허락하지않습니다.");
        }

        // 매장 매상 추가
        StoreSales storeSales = storeSalesRepository.findStoreSalesById(itemEntity.getStoreId())
                                                    .orElseThrow(() -> new NotFoundException("요청하신 StoreId : " + itemEntity.getStoreId() + "에 해당하는 StoreSale 없습니다.") );

        storeSalesRepository.updateSalesAmount(itemEntity.getStoreId(), storeSales.getAmount() + totalPrice);

        return successBuyItemNums;
    }
}

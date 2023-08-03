package com.github.supercodingspring.service.mapper;

import com.github.supercodingspring.repository.items.ItemEntity;
import com.github.supercodingspring.repository.storeSales.StoreSales;
import com.github.supercodingspring.web.dto.items.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ItemMapperUtilTest {

    @DisplayName("ItemEntity의 itemEntityToItem 메소드 테스트")
    @Test
    void ItemEntityToItem() {
        // given
        ItemEntity itemEntity = ItemEntity.builder()
                                          .name("name")
                                          .type("type")
                                          .id(1)
                                          .price(1000)
                                          .stock(0)
                                          .cpu("CPU 1")
                                          .capacity("5G")
                                          .storeSales(new StoreSales())
                                          .build();

        // when
        Item item = ItemMapper.INSTANCE.itemEntityToItem(itemEntity);

        // then
        log.info("만들어진 item: " + item);
        assertEquals(itemEntity.getPrice(), item.getPrice());
        assertEquals(itemEntity.getId().toString(), item.getId());
        assertEquals(itemEntity.getCapacity(), item.getSpec().getCapacity());
        assertEquals(itemEntity.getCpu(), item.getSpec().getCpu());
    }
}
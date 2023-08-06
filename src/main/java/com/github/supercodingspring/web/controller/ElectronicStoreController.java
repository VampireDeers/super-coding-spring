package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.service.ElectronicStoreItemService;
import com.github.supercodingspring.web.dto.items.BuyOrder;
import com.github.supercodingspring.web.dto.items.Item;
import com.github.supercodingspring.web.dto.items.ItemBody;
import com.github.supercodingspring.web.dto.items.StoreInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ElectronicStoreController {

    private final ElectronicStoreItemService electronicStoreItemService;

    @ApiOperation("모든 Items을 검색")
    @GetMapping("/items")
    public List<Item> findAllItem(){
        List<Item> items = electronicStoreItemService.findAllItem();
        return items;
    }

    @ApiOperation("단일 Item 등록")
    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody){
//        log.info("POST /items 요청이 들어왔습니다.");
        Integer itemId = electronicStoreItemService.savaItem(itemBody);
//        log.info("POST /items 응답 ID: " + itemId);
        return "ID: " + itemId;
    }

    @ApiOperation("단일 Item id로 검색")
    @GetMapping("/items/{id}")
    public Item findItemByPathId(
            @ApiParam(name = "id", value = "item ID", example = "1") @PathVariable String id){
        Item item = electronicStoreItemService.findItemById(id);
        return item;
    }

    @ApiOperation("단일 Item id로 검색 (쿼리문)")
    @GetMapping("/items-query")
    public Item findItemByQueryId(
            @ApiParam(name = "id", value = "item ID", example = "1")
            @RequestParam("id") String id){
        Item item = electronicStoreItemService.findItemById(id);
        return item;
    }

    @ApiOperation("여러 Item ids로 검색 (쿼리문)")
    @GetMapping("/items-queries")
    public List<Item> findItemByQueryIds(@ApiParam(name = "ids", value = "item IDs", example = "[1,2,3]") @RequestParam("id") List<String> ids){
        List<Item> items = electronicStoreItemService.findItemsByIds(ids);
        return items;
    }

    @ApiOperation("단일 Item id로 삭제")
    @DeleteMapping("/items/{id}")
    public String deleteItemByPathId(@ApiParam(name = "id", value = "item ID", example = "1") @PathVariable String id){
        electronicStoreItemService.deleteItem(id);
        String responseMessage = "Object with id = " + id + " has been deleted";
        return responseMessage;
    }

    @ApiOperation("단일 Item id 수정")
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody ItemBody itemBody){
//        log.info("PUT /items/" + id + " 요청이 들어왔습니다.");
        Item updatedItem = electronicStoreItemService.updateItem(id, itemBody);
//        log.info("PUT /items/" + id + " 응답: " + updatedItem);
        return updatedItem;
    }

    @ApiOperation("단일 Item 구매")
    @PostMapping("/items/buy")
    public String buyItem(@RequestBody BuyOrder buyOrder){
//        log.info("POST /items/buy 요청이 들어왔습니다.");
        Integer orderItemNums = electronicStoreItemService.buyItems(buyOrder);
        String responseMessage = "요청하신 Item 중 " + orderItemNums + "개를 구매 하였습니다.";
//        log.info("POST /items/buy 응답: " + responseMessage);
        return responseMessage;
    }

    @ApiOperation("type 검색")
    @GetMapping("/items-type")
    public List<Item> findItemsByType(@RequestParam("type") List<String> types){
//        log.info("GET /items-type 요청이 들어왔습니다.");
        List<Item> items = electronicStoreItemService.findItemsByTypes(types);
//        log.info("GET /items-type 응답: " + items);
        return items;
    }

    @ApiOperation("가성비 싼 거부터 검색")
    @GetMapping("/items-prices")
    public List<Item> findItemsByPricing(@RequestParam("max") Integer maxPrice){
//        log.info("GET /items-prices 요청이 들어왔습니다.");
        List<Item> items = electronicStoreItemService.findItemsOrderByPrices(maxPrice);
//        log.info("GET /items-prices 응답: " + items);
        return items;
    }

    @ApiOperation("pagnation 지원")
    @GetMapping("/items-page")
    public Page<Item> findItemsPagination(Pageable pageable){
//        log.info("GET /items-page 요청이 들어왔습니다.");
        Page<Item> items = electronicStoreItemService.findAllWithPageable(pageable);
//        log.info("GET /items-page 응답: " + items);
        return items;
    }

    @ApiOperation("pagnation 지원")
    @GetMapping("/items-types-page")
    public Page<Item> findItemsTypesPagination(@RequestParam("type") List<String> types, Pageable pageable){
//        log.info("GET /items-types-page 요청이 들어왔습니다.");
        Page<Item> items = electronicStoreItemService.findAllWithPageable(types, pageable);
//        log.info("GET /items-types-page 응답: " + items);
        return items;
    }

    @ApiOperation("전체 stores 정보 검색")
    @GetMapping("/stores")
    public List<StoreInfo> findAllStoreInfo(){
//        log.info("GET /stores 요청이 들어왔습니다.");
        List<StoreInfo> storeInfos = electronicStoreItemService.findAllStoreInfo();
//        log.info("GET /stores 응답: " + storeInfos);
        return storeInfos;
    }
}
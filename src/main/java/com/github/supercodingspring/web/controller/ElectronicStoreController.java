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

    @ApiOperation("단일 Item id로 검색 (쿼리문)")
    @GetMapping("/items-query")
    public Item findItemByQueryId(
            @ApiParam(name = "id", value = "item ID", example = "1")
            @RequestParam("id") String id){
        log.info("GET /items-query 요청이 들어왔습니다.");
        Item item = electronicStoreItemService.findItemById(id);
        log.info("GET /items-query 응답: " + item);
        return item;
    }

    @ApiOperation("type 검색")
    @GetMapping("/items-type")
    public List<Item> findItemsByType(@RequestParam("type") List<String> types){
        log.info("GET /items-type 요청이 들어왔습니다.");
        List<Item> items = electronicStoreItemService.findItemsByTypes(types);
        log.info("GET /items-type 응답: " + items);
        return items;
    }

    @ApiOperation("가성비 싼 거부터 검색")
    @GetMapping("/items-prices")
    public List<Item> findItemsByPricing(@RequestParam("max") Integer maxPrice){
        log.info("GET /items-prices 요청이 들어왔습니다.");
        List<Item> items = electronicStoreItemService.findItemsOrderByPrices(maxPrice);
        log.info("GET /items-prices 응답: " + items);
        return items;
    }

    @ApiOperation("pagnation 지원")
    @GetMapping("/items-page")
    public Page<Item> findItemsPagination(Pageable pageable){
        log.info("GET /items-page 요청이 들어왔습니다.");
        Page<Item> itemsPage = electronicStoreItemService.findAllWithPageable(pageable);
        log.info("GET /items-page 응답: " + itemsPage);
        return itemsPage;
    }

    @ApiOperation("pagnation 지원")
    @GetMapping("/items-types-page")
    public Page<Item> findItemsTypesPagination(@RequestParam("type") List<String> types ,Pageable pageable){
        log.info("GET /items-types-page 요청이 들어왔습니다.");
        Page<Item> itemsPage = electronicStoreItemService.findAllWithPageable(types, pageable);
        log.info("GET /items-types-page 응답: " + itemsPage);
        return itemsPage;
    }

    @ApiOperation("전체 stores 정보 검색")
    @GetMapping("/stores")
    public List<StoreInfo> findAllStoreInfo(){
        log.info("GET /stores 요청이 들어왔습니다.");
        List<StoreInfo> storeInfos = electronicStoreItemService.findAllStoreInfo();
        log.info("GET /stores 응답: " + storeInfos);
        return storeInfos;
    }
}



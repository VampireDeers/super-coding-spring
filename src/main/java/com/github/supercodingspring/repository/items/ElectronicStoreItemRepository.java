package com.github.supercodingspring.repository.items;

import java.util.List;
import java.util.Optional;

public interface ElectronicStoreItemRepository {

    List<ItemEntity> findAllItems();

    Integer saveItem(ItemEntity itemEntity);

    ItemEntity updateItemEntity(Integer idInt, ItemEntity itemEntity);

    void deleteItem(int parseInt);

    Optional<ItemEntity> findItemById(Integer idInt);
    Optional<ItemEntity> findItemByName(String name);
    void updateItemStock(Integer itemId, Integer i);
}

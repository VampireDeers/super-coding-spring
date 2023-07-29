package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.web.dto.Item;
import com.github.supercodingspring.web.dto.ItemBody;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ElectronicStoreController {

    private static int serialItemId = 1;
    private List<Item> items = Arrays.asList(new Item(String.valueOf(serialItemId++), "Apple iPhone 12 Pro Max", "Smartphone", 1490000, "A14 Bionic", "512GB"),
                                             new Item(String.valueOf(serialItemId++), "Samsung Galaxy S21 Ultra", "Smartphone", 1690000, "Exynos 2100", "256GB"),
                                             new Item(String.valueOf(serialItemId++), "Google Pixel 6 Pro", "Smartphone", 1290000, "Google Tensor", "128GB"),
                                             new Item(String.valueOf(serialItemId++), "Dell XPS 15", "Laptop", 2290000, "Intel Core i9", "1TB SSD"),
                                             new Item(String.valueOf(serialItemId++), "Sony Alpha 7 III", "Mirrorless Camera", 2590000, "BIONZ X", "No internal storage"),
                                             new Item(String.valueOf(serialItemId++), "Microsoft Xbox Series X", "Gaming Console", 499000, "Custom AMD Zen 2", "1TB SSD"));

    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody) {
        Item item = new Item(String.valueOf(serialItemId++), itemBody);
        items.add(item);
        return item.getId();
    }

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return items;
    }

    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable String id) {
        Item itemFounded = items.stream()
            .filter((item) -> item.getId().equals(id))
            .findFirst()
            .orElseThrow();
        return itemFounded;
    }

    @GetMapping("/items-query")
    public Item getItemByQueryId(@RequestParam("id") String id) {
        Item itemFounded = items.stream()
            .filter((item) -> item.getId().equals(id))
            .findFirst()
            .orElseThrow();
        return itemFounded;
    }

    @GetMapping("/items-queries")
    public List<Item> getItemByQueryId(@RequestParam("id") List<String> ids) {
        List<Item> foundItems = items.stream()
            .filter((item) -> ids.contains(item.getId()))
            .collect(Collectors.toList());
        return foundItems;
    }

    @DeleteMapping("/items/{id}")
    public String deleteItemById(@PathVariable String id) {
        items = items.stream()
            .filter((item) -> !item.getId().equals(id))
            .collect(Collectors.toList());
        return "Object with id =" + id + "has been deleted";
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody ItemBody itemBody) {
        Item itemFounded = items.stream()
            .filter((item) -> item.getId().equals(id))
            .findFirst()
            .orElseThrow();

        items.remove(itemFounded);

        Item newItem = new Item(id, itemBody);
        items.add(newItem);

        return newItem;
    }
}

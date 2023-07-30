package com.github.supercodingspring.web.dto.items;

import com.github.supercodingspring.repository.items.ItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class Item {

    private String id;
    private String name;
    private String type;
    private Integer price;
    private Spec spec;


    public Item(String id, ItemBody itemBody){
        this.id = id;
        this.name = itemBody.getName();
        this.type = itemBody.getType();
        this.price = itemBody.getPrice();
        this.spec = itemBody.getSpec();
    }

    public Item(ItemEntity itemEntity){
        this.id = itemEntity.getId().toString();
        this.type = itemEntity.getType();
        this.price = itemEntity.getPrice();
        this.name = itemEntity.getName();
        this.spec = new Spec(itemEntity.getCpu(), itemEntity.getCapacity());
    }

}

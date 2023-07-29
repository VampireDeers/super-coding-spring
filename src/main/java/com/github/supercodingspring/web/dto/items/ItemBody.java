package com.github.supercodingspring.web.dto.items;

public class ItemBody {

    private String name;
    private String type;
    private Integer price;
    private Spec spec;

    public ItemBody() {
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    public Spec getSpec() {
        return spec;
    }
}

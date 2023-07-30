package com.github.supercodingspring.web.dto.items;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemBody {
    @ApiModelProperty(name = "name", value = "Item 이름", example = "Dell XPS 15") private String name;
    @ApiModelProperty(name = "type", value = "Item 기기타입", example = "Laptop") private String type;
    @ApiModelProperty(name = "price", value = "Item 가격", example = "125000") private Integer price;
    private Spec spec;
}

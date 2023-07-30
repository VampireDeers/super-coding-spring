package com.github.supercodingspring.web.dto.items;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuyOrder {
    @ApiModelProperty(name = "itemId", value = "Item ID", example = "1") private Integer itemId;
    @ApiModelProperty(name = "itemNums", value = "Item 주문 갯수", example = "5") private Integer itemNums;
}

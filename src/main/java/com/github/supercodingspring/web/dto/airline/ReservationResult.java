package com.github.supercodingspring.web.dto.airline;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResult {
    @ApiModelProperty(name = "prices", value = "Flight 가격들", example = "[\"12000\", \"3000\"]") private List<Integer> prices;
    @ApiModelProperty(name = "charges", value = "Flight 추가 비용들", example = "[\"100000\", \"30000\"]") private List<Integer> charges;
    @ApiModelProperty(name = "tax", value = "항공권 세금", example = "12300") private Integer tax;
    @ApiModelProperty(name = "totalPrice", value = "모든 비용 총합", example = "12500")private Integer totalPrice;
    @ApiModelProperty(name = "success", value = "예약 성공 상태", example = "true")private Boolean success;
}


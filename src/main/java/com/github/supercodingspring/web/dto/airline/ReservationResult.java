package com.github.supercodingspring.web.dto.airline;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationResult {
    private List<Integer> prices;
    private List<Integer> charges;
    private Integer tax;
    private Integer totalPrice;
    private Boolean success;

    public ReservationResult(List<Integer> prices, List<Integer> charges, Integer tax, Integer totalPrice, Boolean success) {
        this.prices = prices;
        this.charges = charges;
        this.tax = tax;
        this.totalPrice = totalPrice;
        this.success = success;
    }

    public ReservationResult() {
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public List<Integer> getCharges() {
        return charges;
    }

    public Integer getTax() {
        return tax;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Boolean getSuccess() {
        return success;
    }
}


package com.github.supercodingspring.repository.reservations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightPriceAndCharge {
    private Double flightPrice;
    private Double charge;
}

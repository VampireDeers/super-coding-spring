package com.github.supercodingspring.repository.reservations;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightPriceAndCharge {
    private Double flightPrice;
    private Double charge;
}

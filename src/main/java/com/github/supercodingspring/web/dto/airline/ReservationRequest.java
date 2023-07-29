package com.github.supercodingspring.web.dto.airline;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationRequest {
    private Integer userId;
    private Integer airlineTicketId;

    public ReservationRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getAirlineTicketId() {
        return airlineTicketId;
    }
}


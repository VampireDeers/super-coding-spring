package com.github.supercodingspring.web.dto.airline;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TicketResponse {
    @ApiModelProperty(name = "tickets", value = "tickets 들") private List<Ticket> tickets;
}

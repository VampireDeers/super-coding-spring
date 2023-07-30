package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.service.AirReservationService;
import com.github.supercodingspring.web.dto.airline.ReservationRequest;
import com.github.supercodingspring.web.dto.airline.ReservationResult;
import com.github.supercodingspring.web.dto.airline.Ticket;
import com.github.supercodingspring.web.dto.airline.TicketResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/air-reservation")
@RequiredArgsConstructor
public class AirReservationController {

    private final AirReservationService airReservationService;

    @ApiOperation("선호하는 ticket 탐색")
    @GetMapping("/tickets")
    public TicketResponse findAirlineTickets(
            @ApiParam(name = "user-Id", value = "유저 ID", example = "1") @RequestParam("user-Id") Integer userId,
            @ApiParam(name = "airline-ticket-type", value = "항공권 타입", example = "왕복|편도") @RequestParam("airline-ticket-type") String ticketType ){
        List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
        return new TicketResponse(tickets);
    }
    @ApiOperation("User와 Ticket Id로 결제 진행")
    @PostMapping("/reservations")
    public ReservationResult makeReservation(@RequestBody ReservationRequest reservationRequest){
        return airReservationService.makeReservation(reservationRequest);
    }
}

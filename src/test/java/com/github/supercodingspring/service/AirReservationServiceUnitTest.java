package com.github.supercodingspring.service;

import com.github.supercodingspring.repository.airlineTicket.AirlineTicket;
import com.github.supercodingspring.repository.airlineTicket.AirlineTicketJpaRepository;
import com.github.supercodingspring.repository.airlineTicket.AirlineTicketRepository;
import com.github.supercodingspring.repository.users.UserEntity;
import com.github.supercodingspring.repository.users.UserJpaRepository;
import com.github.supercodingspring.service.exceptions.InvalidValueException;
import com.github.supercodingspring.service.exceptions.NotFoundException;
import com.github.supercodingspring.web.dto.airline.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
class AirReservationServiceUnitTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private AirlineTicketJpaRepository airlineTicketJpaRepository;

    @InjectMocks
    private AirReservationService airReservationService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("airlineTicket에 해당하는 유저 항공권들이 모두 있어서 성공하는 경우")
    @Test
    void FindUserFavoritePlaceTicketsCase1() {
        // given
        Integer userId = 5;
        String likePlace = "파리";
        String ticketType = "왕복";

        UserEntity userEntity = UserEntity.builder()
                                          .userId(userId)
                                          .likeTravelPlace(likePlace)
                                          .userName("name1")
                                          .phoneNum("1234")
                                          .build();

        List<AirlineTicket> airlineTickets = Arrays.asList(
                AirlineTicket.builder()
                             .ticketId(1)
                             .arrivalLocation(likePlace)
                             .ticketType(ticketType)
                             .build(),
                AirlineTicket.builder()
                             .ticketId(2)
                             .arrivalLocation(likePlace)
                             .ticketType(ticketType)
                             .build(),
                AirlineTicket.builder()
                             .ticketId(3)
                             .arrivalLocation(likePlace)
                             .ticketType(ticketType)
                             .build(),
                AirlineTicket.builder()
                             .ticketId(4)
                             .arrivalLocation(likePlace)
                             .ticketType(ticketType)
                             .build()
        );

        // when
        when(userJpaRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(airlineTicketJpaRepository.findAirlineTicketsByArrivalLocationAndTicketType(likePlace, ticketType))
                .thenReturn(airlineTickets);

        // then
        List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
        log.info("tickets: " + tickets);
        assertTrue(
                tickets.stream()
                       .map(Ticket::getArrival)
                       .allMatch((arrival) -> arrival.equals(likePlace))
        );
    }

    @DisplayName("TicketType이 왕복 | 편도가 아닌 경우, Exception 발생해야함 ")
    @Test
    void FindUserFavoritePlaceTicketsCase2() {
        // given
        Integer userId = 5;
        String likePlace = "파리";
        String ticketType = "왕";

        UserEntity userEntity = UserEntity.builder()
                                          .userId(userId)
                                          .likeTravelPlace(likePlace)
                                          .userName("name1")
                                          .phoneNum("1234")
                                          .build();

        List<AirlineTicket> airlineTickets = Arrays.asList(
                AirlineTicket.builder().ticketId(1).arrivalLocation(likePlace).ticketType(ticketType).build(),
                AirlineTicket.builder().ticketId(2).arrivalLocation(likePlace).ticketType(ticketType).build(),
                AirlineTicket.builder().ticketId(3).arrivalLocation(likePlace).ticketType(ticketType).build(),
                AirlineTicket.builder().ticketId(4).arrivalLocation(likePlace).ticketType(ticketType).build()
        );

        // when
        when(userJpaRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(airlineTicketJpaRepository.findAirlineTicketsByArrivalLocationAndTicketType(likePlace, ticketType))
                .thenReturn(airlineTickets);

        // then
        assertThrows(InvalidValueException.class,
                     () -> airReservationService.findUserFavoritePlaceTickets(userId, ticketType)
        );
    }

    @DisplayName("AirlineTickets를 찾을 수 없는 경우, Exception 발생해야 함 ")
    @Test
    void FindUserFavoritePlaceTicketsCase3() {
        // given
        Integer userId = 5;
        String likePlace = "파리";
        String ticketType = "왕복";

        UserEntity userEntity = UserEntity.builder()
                                          .userId(userId)
                                          .likeTravelPlace(likePlace)
                                          .userName("name1")
                                          .phoneNum("1234")
                                          .build();

        List<AirlineTicket> airlineTickets = new ArrayList<>();

        // when
        when(userJpaRepository.findById(any())).thenReturn(Optional.ofNullable(userEntity));
        when(airlineTicketJpaRepository.findAirlineTicketsByArrivalLocationAndTicketType(likePlace, ticketType))
                .thenReturn(airlineTickets);

        // then
        assertThrows(NotFoundException.class,
                     () -> airReservationService.findUserFavoritePlaceTickets(userId, ticketType)
        );
    }

    @DisplayName("User를 찾을 수 없는 경우, Exception 발생해야 함  ")
    @Test
    void FindUserFavoritePlaceTicketsCase4() {
        // given
        Integer userId = 5;
        String likePlace = "파리";
        String ticketType = "왕복";

        UserEntity userEntity = null;

        List<AirlineTicket> airlineTickets = Arrays.asList(
                AirlineTicket.builder().ticketId(1).arrivalLocation(likePlace).ticketType(ticketType).build(),
                AirlineTicket.builder().ticketId(2).arrivalLocation(likePlace).ticketType(ticketType).build(),
                AirlineTicket.builder().ticketId(3).arrivalLocation(likePlace).ticketType(ticketType).build(),
                AirlineTicket.builder().ticketId(4).arrivalLocation(likePlace).ticketType(ticketType).build()
        );

        // when
        when(userJpaRepository.findById(any())).thenReturn(Optional.ofNullable(userEntity));
        when(airlineTicketJpaRepository.findAirlineTicketsByArrivalLocationAndTicketType(likePlace, ticketType))
                .thenReturn(airlineTickets);

        // then
        assertThrows(NotFoundException.class,
                     () -> airReservationService.findUserFavoritePlaceTickets(userId, ticketType)
        );
    }
}
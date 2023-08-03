package com.github.supercodingspring.repository.reservations;

import com.github.supercodingspring.repository.airlineTicket.AirlineTicket;
import com.github.supercodingspring.repository.airlineTicket.AirlineTicketJpaRepository;
import com.github.supercodingspring.repository.passenger.Passenger;
import com.github.supercodingspring.repository.passenger.PassengerJpaRepository;
import com.github.supercodingspring.service.AirReservationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // slice test => Dao Lay / Jpa 사용하고 있는 Slice Test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ReservationJpaRepositoryJpaTest {

//    @Autowired
//    private AirReservationService airReservationService;

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @Autowired
    private PassengerJpaRepository passengerJpaRepository;

    @Autowired
    private AirlineTicketJpaRepository airlineTicketJpaRepository;

    @DisplayName("ReservationRepository로 항공편 가격과 수수료 검색")
    @Test
    void FindFlightPriceAndCharge() {
        // given
        Integer userId = 10;

        // when
        List<FlightPriceAndCharge> flightPriceAndCharges = reservationJpaRepository.findFlightPriceAndCharge(userId);

        // then
        log.info("결과: " + flightPriceAndCharges);
    }

    @DisplayName("Reservation 예약 진행")
    @Test
    void saveReservation(){
        // given
        Integer userId = 10;
        Integer ticketId = 5;

        Passenger passenger = passengerJpaRepository.findPassengerByUserUserId(userId).get();
        AirlineTicket airlineTicket = airlineTicketJpaRepository.findById(5).get();

        // when
        Reservation reservation = new Reservation(passenger, airlineTicket);
        Reservation res = reservationJpaRepository.save(reservation);

        // then
        log.info("결과: " + res);
        assertEquals(res.getPassenger(), passenger);
        assertEquals(res.getAirlineTicket(), airlineTicket);

    }
}
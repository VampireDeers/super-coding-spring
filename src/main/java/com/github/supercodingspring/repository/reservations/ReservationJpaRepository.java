package com.github.supercodingspring.repository.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Integer> {


    @Query("SELECT new com.github.supercodingspring.repository.reservations.FlightPriceAndCharge(f.flightPrice, f.charge) " +
           "FROM Reservation r " +
           "JOIN r.passenger p " +
           "JOIN r.airlineTicket a " +
           "JOIN a.flightList f " +
           "WHERE p.user.userId = :userId ")
    List<FlightPriceAndCharge> findFlightPriceAndCharge(Integer userId);
}

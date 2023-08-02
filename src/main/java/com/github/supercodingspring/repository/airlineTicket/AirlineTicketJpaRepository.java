package com.github.supercodingspring.repository.airlineTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineTicketJpaRepository extends JpaRepository<AirlineTicket, Integer> {

    List<AirlineTicket> findAirlineTicketsByArrivalLocationAndTicketType(String likePlace, String ticketType);
}

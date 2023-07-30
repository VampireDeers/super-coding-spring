package com.github.supercodingspring.repository.airlineTicket;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirlineTicketJdbcTemplateDao implements AirlineTicketRepository {
    private JdbcTemplate jdbcTemplate;

    public AirlineTicketJdbcTemplateDao(@Qualifier("jdbcTemplate2") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static RowMapper<AirlineTicket> airlineTicketRowMapper = (((rs, rowNum) ->
            new AirlineTicket.AirlineTicketBuilder()
                    .ticketId(rs.getInt("ticket_id"))
                    .ticketType(rs.getString("ticket_type"))
                    .departureLocation(rs.getNString("departure_loc"))
                    .arrivalLocation(rs.getNString("arrival_loc"))
                    .departureAt(rs.getDate("departure_at"))
                    .returnAt(rs.getDate("return_at"))
                    .tax(rs.getDouble("tax"))
                    .totalPrice(rs.getDouble("total_price"))
                    .build()));
    static RowMapper<AirlineTicketAndFlightInfo> airlineTicketAndFlightInfoRowMapper
            = (((rs, rowNum) ->
                new AirlineTicketAndFlightInfo.AirlineTicketAndFlightInfoBuilder()
                        .ticketId(rs.getInt("A.ticket_id"))
                        .price(rs.getDouble("F.flight_price"))
                        .charge(rs.getDouble("F.charge"))
                        .tax(rs.getDouble("A.tax"))
                        .totalPrice(rs.getDouble("A.total_price"))
                        .build()));

    @Override
    public List<AirlineTicket> findAllAirlineTicketsWithPlaceAndTicketType(String likePlace, String ticketType) {
        return jdbcTemplate.query("SELECT * FROM airline_ticket " +
                                  "WHERE arrival_loc = ? AND ticket_type = ?", airlineTicketRowMapper, likePlace, ticketType);
    }

    @Override
    public List<AirlineTicketAndFlightInfo> findAllAirLineTicketAndFlightInfo(Integer airlineTicketId) {
        return jdbcTemplate.query("SELECT A.ticket_id, A.tax, A.total_price, F.flight_price, F.charge " +
                                  "    FROM airline_ticket A " +
                                  "         INNER JOIN flight F " +
                                  "         ON A.ticket_id = F.ticket_id " +
                                  "     WHERE A.ticket_id = ?", airlineTicketAndFlightInfoRowMapper, airlineTicketId);
    }
}

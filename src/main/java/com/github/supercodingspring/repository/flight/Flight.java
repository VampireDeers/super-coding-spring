package com.github.supercodingspring.repository.flight;

import com.github.supercodingspring.repository.airlineTicket.AirlineTicket;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
@Table(name = "flight")
public class Flight {
    @Id @Column(name = "flight_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = true)
    private AirlineTicket airlineTicket;

    @Column(name = "departure_at")
    private LocalDateTime departAt;

    @Column(name = "arrival_at")
    private LocalDateTime arrivalAt;

    @Column(name = "departure_loc", length = 50)
    private String departureLocation;

    @Column(name = "arrival_loc", length = 50)
    private String arrivalLocation;

    @Column(name = "flight_price")
    private Double flightPrice;

    @Column(name = "charge")
    private Double charge;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Flight flight = (Flight) o;
        return flightId != null && Objects.equals(flightId, flight.flightId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

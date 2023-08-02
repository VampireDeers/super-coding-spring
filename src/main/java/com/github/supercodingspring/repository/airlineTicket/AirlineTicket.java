package com.github.supercodingspring.repository.airlineTicket;

import com.github.supercodingspring.repository.flight.Flight;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "airline_ticket")
public class AirlineTicket {
    @Id @Column(name = "ticket_id")
    private Integer ticketId;
    @Column(name = "ticket_type", length = 5, columnDefinition = "CHECK (ticket_type in ('편도', '왕복')) ")
    private String ticketType;
    @Column(name = "departure_loc", length = 20)
    private String departureLocation;
    @Column(name = "arrival_loc", length = 20)
    private String arrivalLocation;

    @Column(name = "departure_at", nullable = false)
    private LocalDateTime departureAt;
    @Column(name = "return_at", nullable = false)
    private LocalDateTime returnAt;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "airlineTicket")
    private List<Flight> flightList;


    @Builder
    public AirlineTicket(Integer ticketId, String ticketType, String departureLocation, String arrivalLocation, Date departureAt, Date returnAt, Double tax, Double totalPrice) {
        this.ticketId = ticketId;
        this.ticketType = ticketType;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureAt = departureAt.toLocalDate().atStartOfDay();
        this.returnAt = returnAt.toLocalDate().atStartOfDay();
        this.tax = tax;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        AirlineTicket that = (AirlineTicket) o;
        return ticketId != null && Objects.equals(ticketId, that.ticketId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

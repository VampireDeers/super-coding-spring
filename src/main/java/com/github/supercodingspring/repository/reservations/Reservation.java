package com.github.supercodingspring.repository.reservations;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id @Column(name = "reservation_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(name = "passenger_id")
    private Integer passengerId;
    @Column(name = "airline_ticket_id")
    private Integer airlineTicketId;

    @Column(name = "reservation_status", length = 10)
    private String reservationStatus;

    @Column(name = "reserve_at")
    private LocalDateTime reserveAt;

    public Reservation(Integer passengerId, Integer airlineTicketId) {
        this.passengerId = passengerId;
        this.airlineTicketId = airlineTicketId;
        this.reservationStatus = "대기";
        this.reserveAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Reservation that = (Reservation) o;
        return reservationId != null && Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

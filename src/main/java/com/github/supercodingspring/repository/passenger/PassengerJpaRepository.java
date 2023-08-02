package com.github.supercodingspring.repository.passenger;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerJpaRepository extends JpaRepository<Passenger, Integer> {

    Optional<Passenger> findPassengerByUserUserId(Integer userId);

}

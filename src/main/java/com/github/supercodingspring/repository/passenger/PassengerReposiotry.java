package com.github.supercodingspring.repository.passenger;

import java.util.Optional;

public interface PassengerReposiotry {
    Optional<Passenger> findPassengerByUserId(Integer userId);
}

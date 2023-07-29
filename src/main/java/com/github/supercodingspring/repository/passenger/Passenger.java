package com.github.supercodingspring.repository.passenger;

public class Passenger {
    private Integer passengerId;
    private Integer userId;
    private String passportNum;

    public Passenger(Integer passengerId, Integer userId, String passportNum) {
        this.passengerId = passengerId;
        this.userId = userId;
        this.passportNum = passportNum;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Passenger)) {
            return false;
        }

        Passenger passenger = (Passenger) o;

        return passengerId.equals(passenger.passengerId);
    }

    @Override
    public int hashCode() {
        return passengerId.hashCode();
    }
}

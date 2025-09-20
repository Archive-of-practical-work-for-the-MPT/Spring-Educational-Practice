package com.example.project2.service;

import com.example.project2.model.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> findAllPassengers();
    Passenger createPassenger(Passenger passenger);
    Passenger updatePassenger(Passenger passenger);
    Passenger findPassengerById(Long id);
    void deletePassenger(Long id);
    Passenger findByEmail(String email);
    Passenger findByPassportNumber(String passportNumber);
    boolean existsByEmail(String email);
    boolean existsByPassportNumber(String passportNumber);
    boolean existsByPhone(String phone);
}
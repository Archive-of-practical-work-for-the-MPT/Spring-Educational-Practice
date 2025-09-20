package com.example.project2.repository;

import com.example.project2.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportCode(String departureAirportCode);
    List<Flight> findByArrivalAirportCode(String arrivalAirportCode);
    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
}
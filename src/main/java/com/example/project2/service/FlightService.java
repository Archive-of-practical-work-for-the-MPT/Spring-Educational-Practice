package com.example.project2.service;

import com.example.project2.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<Flight> findAllFlights();
    Flight createFlight(Flight flight);
    Flight updateFlight(Flight flight);
    Flight findFlightById(Long id);
    void deleteFlight(Long id);
    List<Flight> findByDepartureAirportCode(String departureAirportCode);
    List<Flight> findByArrivalAirportCode(String arrivalAirportCode);
    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
}
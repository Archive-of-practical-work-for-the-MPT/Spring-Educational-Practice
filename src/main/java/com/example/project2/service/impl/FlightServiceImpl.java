package com.example.project2.service.impl;

import com.example.project2.model.Flight;
import com.example.project2.repository.FlightRepository;
import com.example.project2.service.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository repository;

    public FlightServiceImpl(FlightRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Flight> findAllFlights() {
        return repository.findAll();
    }

    @Override
    public Flight createFlight(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public Flight findFlightById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteFlight(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Flight> findByDepartureAirportCode(String departureAirportCode) {
        return repository.findByDepartureAirportCode(departureAirportCode);
    }

    @Override
    public List<Flight> findByArrivalAirportCode(String arrivalAirportCode) {
        return repository.findByArrivalAirportCode(arrivalAirportCode);
    }

    @Override
    public List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByDepartureTimeBetween(start, end);
    }
}
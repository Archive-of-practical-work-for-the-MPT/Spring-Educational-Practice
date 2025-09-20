package com.example.project2.controller.api;

import com.example.project2.model.Flight;
import com.example.project2.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightApiController {

    private final FlightService flightService;

    @Autowired
    public FlightApiController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.findAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.findFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        flight.setId(id);
        return flightService.updateFlight(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @GetMapping("/departure/{airportCode}")
    public List<Flight> getFlightsByDepartureAirport(@PathVariable String airportCode) {
        return flightService.findByDepartureAirportCode(airportCode);
    }

    @GetMapping("/arrival/{airportCode}")
    public List<Flight> getFlightsByArrivalAirport(@PathVariable String airportCode) {
        return flightService.findByArrivalAirportCode(airportCode);
    }

    @GetMapping("/date-range")
    public List<Flight> getFlightsByDateRange(
            @RequestParam String start,
            @RequestParam String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endTime = LocalDateTime.parse(end, formatter);
        return flightService.findByDepartureTimeBetween(startTime, endTime);
    }
}
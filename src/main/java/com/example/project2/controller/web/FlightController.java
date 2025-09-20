package com.example.project2.controller.web;

import com.example.project2.model.Flight;
import com.example.project2.service.FlightService;
import com.example.project2.service.AirportService;
import com.example.project2.service.AircraftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final AirportService airportService;
    private final AircraftService aircraftService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService, AircraftService aircraftService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public String getAllFlights(Model model) {
        List<Flight> flights = flightService.findAllFlights();
        model.addAttribute("flights", flights);
        model.addAttribute("flight", new Flight());
        model.addAttribute("airports", airportService.findAllAirports());
        model.addAttribute("aircrafts", aircraftService.findAllAircrafts());
        return "flights/flights";
    }

    @PostMapping
    public String addFlight(@Valid @ModelAttribute Flight flight, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Flight> flights = flightService.findAllFlights();
            model.addAttribute("flights", flights);
            model.addAttribute("flight", flight);
            model.addAttribute("airports", airportService.findAllAirports());
            model.addAttribute("aircrafts", aircraftService.findAllAircrafts());
            return "flights/flights";
        }
        flightService.createFlight(flight);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{id}")
    public String editFlight(@PathVariable Long id, Model model) {
        Flight flight = flightService.findFlightById(id);
        model.addAttribute("flight", flight);
        model.addAttribute("airports", airportService.findAllAirports());
        model.addAttribute("aircrafts", aircraftService.findAllAircrafts());
        return "flights/editFlight";
    }

    @PostMapping("/update")
    public String updateFlight(@Valid @ModelAttribute Flight flight, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Flight> flights = flightService.findAllFlights();
            model.addAttribute("flights", flights);
            model.addAttribute("flight", flight);
            model.addAttribute("airports", airportService.findAllAirports());
            model.addAttribute("aircrafts", aircraftService.findAllAircrafts());
            return "flights/flights";
        }
        flightService.updateFlight(flight);
        return "redirect:/flights";
    }

    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
}
package com.example.project2.controller.web;

import com.example.project2.model.Airport;
import com.example.project2.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public String getAllAirports(Model model) {
        List<Airport> airports = airportService.findAllAirports();
        model.addAttribute("airports", airports);
        model.addAttribute("airport", new Airport());
        return "airports/airports";
    }

    @PostMapping
    public String addAirport(@Valid @ModelAttribute Airport airport, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Airport> airports = airportService.findAllAirports();
            model.addAttribute("airports", airports);
            model.addAttribute("airport", airport);
            return "airports/airports";
        }
        airportService.createAirport(airport);
        return "redirect:/airports";
    }

    @GetMapping("/edit/{id}")
    public String editAirport(@PathVariable Long id, Model model) {
        Airport airport = airportService.findAirportById(id);
        model.addAttribute("airport", airport);
        return "airports/editAirport";
    }

    @PostMapping("/update")
    public String updateAirport(@Valid @ModelAttribute Airport airport, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Airport> airports = airportService.findAllAirports();
            model.addAttribute("airports", airports);
            model.addAttribute("airport", airport);
            return "airports/airports";
        }
        airportService.updateAirport(airport);
        return "redirect:/airports";
    }

    @GetMapping("/delete/{id}")
    public String deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return "redirect:/airports";
    }
}
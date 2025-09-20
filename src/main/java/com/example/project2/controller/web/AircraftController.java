package com.example.project2.controller.web;

import com.example.project2.model.Aircraft;
import com.example.project2.service.AircraftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/aircrafts")
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public String getAllAircrafts(Model model) {
        List<Aircraft> aircrafts = aircraftService.findAllAircrafts();
        model.addAttribute("aircrafts", aircrafts);
        model.addAttribute("aircraft", new Aircraft());
        return "aircrafts/aircrafts";
    }

    @PostMapping
    public String addAircraft(@Valid @ModelAttribute Aircraft aircraft, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Aircraft> aircrafts = aircraftService.findAllAircrafts();
            model.addAttribute("aircrafts", aircrafts);
            model.addAttribute("aircraft", aircraft);
            return "aircrafts/aircrafts";
        }
        aircraftService.createAircraft(aircraft);
        return "redirect:/aircrafts";
    }

    @GetMapping("/edit/{id}")
    public String editAircraft(@PathVariable Long id, Model model) {
        Aircraft aircraft = aircraftService.findAircraftById(id);
        model.addAttribute("aircraft", aircraft);
        return "aircrafts/editAircraft";
    }

    @PostMapping("/update")
    public String updateAircraft(@Valid @ModelAttribute Aircraft aircraft, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Aircraft> aircrafts = aircraftService.findAllAircrafts();
            model.addAttribute("aircrafts", aircrafts);
            model.addAttribute("aircraft", aircraft);
            return "aircrafts/aircrafts";
        }
        aircraftService.updateAircraft(aircraft);
        return "redirect:/aircrafts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
        return "redirect:/aircrafts";
    }
}
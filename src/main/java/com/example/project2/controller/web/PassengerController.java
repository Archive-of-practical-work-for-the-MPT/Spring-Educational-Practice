package com.example.project2.controller.web;

import com.example.project2.model.Passenger;
import com.example.project2.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public String getAllPassengers(Model model) {
        List<Passenger> passengers = passengerService.findAllPassengers();
        model.addAttribute("passengers", passengers);
        model.addAttribute("passenger", new Passenger());
        return "passengers/passengers";
    }

    @PostMapping
    public String addPassenger(@Valid @ModelAttribute Passenger passenger, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Passenger> passengers = passengerService.findAllPassengers();
            model.addAttribute("passengers", passengers);
            model.addAttribute("passenger", passenger);
            return "passengers/passengers";
        }
        passengerService.createPassenger(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/edit/{id}")
    public String editPassenger(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.findPassengerById(id);
        model.addAttribute("passenger", passenger);
        return "passengers/editPassenger";
    }

    @PostMapping("/update")
    public String updatePassenger(@Valid @ModelAttribute Passenger passenger, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Passenger> passengers = passengerService.findAllPassengers();
            model.addAttribute("passengers", passengers);
            model.addAttribute("passenger", passenger);
            return "passengers/passengers";
        }
        passengerService.updatePassenger(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/delete/{id}")
    public String deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return "redirect:/passengers";
    }
}
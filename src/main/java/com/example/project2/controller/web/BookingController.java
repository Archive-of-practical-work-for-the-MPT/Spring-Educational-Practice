package com.example.project2.controller.web;

import com.example.project2.model.Booking;
import com.example.project2.service.BookingService;
import com.example.project2.service.PassengerService;
import com.example.project2.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final PassengerService passengerService;
    private final FlightService flightService;

    @Autowired
    public BookingController(BookingService bookingService, PassengerService passengerService, FlightService flightService) {
        this.bookingService = bookingService;
        this.passengerService = passengerService;
        this.flightService = flightService;
    }

    @GetMapping
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.findAllBookings();
        model.addAttribute("bookings", bookings);
        model.addAttribute("booking", new Booking());
        model.addAttribute("passengers", passengerService.findAllPassengers());
        model.addAttribute("flights", flightService.findAllFlights());
        return "bookings/bookings";
    }

    @PostMapping
    public String addBooking(@Valid @ModelAttribute Booking booking, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Booking> bookings = bookingService.findAllBookings();
            model.addAttribute("bookings", bookings);
            model.addAttribute("booking", booking);
            model.addAttribute("passengers", passengerService.findAllPassengers());
            model.addAttribute("flights", flightService.findAllFlights());
            return "bookings/bookings";
        }
        bookingService.createBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String editBooking(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findBookingById(id);
        model.addAttribute("booking", booking);
        model.addAttribute("passengers", passengerService.findAllPassengers());
        model.addAttribute("flights", flightService.findAllFlights());
        return "bookings/editBooking";
    }

    @PostMapping("/update")
    public String updateBooking(@Valid @ModelAttribute Booking booking, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Booking> bookings = bookingService.findAllBookings();
            model.addAttribute("bookings", bookings);
            model.addAttribute("booking", booking);
            model.addAttribute("passengers", passengerService.findAllPassengers());
            model.addAttribute("flights", flightService.findAllFlights());
            return "bookings/bookings";
        }
        bookingService.updateBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
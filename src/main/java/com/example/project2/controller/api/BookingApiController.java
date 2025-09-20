package com.example.project2.controller.api;

import com.example.project2.model.Booking;
import com.example.project2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingApiController {

    private final BookingService bookingService;

    @Autowired
    public BookingApiController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.findBookingById(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        booking.setId(id);
        return bookingService.updateBooking(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    @GetMapping("/passenger/{passengerId}")
    public List<Booking> getBookingsByPassenger(@PathVariable Long passengerId) {
        return bookingService.findByPassengerId(passengerId);
    }

    @GetMapping("/flight/{flightId}")
    public List<Booking> getBookingsByFlight(@PathVariable Long flightId) {
        return bookingService.findByFlightId(flightId);
    }

    @GetMapping("/date-range")
    public List<Booking> getBookingsByDateRange(
            @RequestParam String start,
            @RequestParam String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endTime = LocalDateTime.parse(end, formatter);
        return bookingService.findByBookingDateBetween(startTime, endTime);
    }
}
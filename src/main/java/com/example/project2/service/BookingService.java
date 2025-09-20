package com.example.project2.service;

import com.example.project2.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    List<Booking> findAllBookings();
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    Booking findBookingById(Long id);
    void deleteBooking(Long id);
    Booking findByBookingNumber(String bookingNumber);
    List<Booking> findByPassengerId(Long passengerId);
    List<Booking> findByFlightId(Long flightId);
    List<Booking> findByBookingDateBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByBookingNumber(String bookingNumber);
}
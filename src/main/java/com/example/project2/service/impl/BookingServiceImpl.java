package com.example.project2.service.impl;

import com.example.project2.model.Booking;
import com.example.project2.repository.BookingRepository;
import com.example.project2.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Booking> findAllBookings() {
        return repository.findAll();
    }

    @Override
    public Booking createBooking(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Booking findBookingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Booking findByBookingNumber(String bookingNumber) {
        return repository.findByBookingNumber(bookingNumber).orElse(null);
    }

    @Override
    public List<Booking> findByPassengerId(Long passengerId) {
        return repository.findByPassengerId(passengerId);
    }

    @Override
    public List<Booking> findByFlightId(Long flightId) {
        return repository.findByFlightId(flightId);
    }

    @Override
    public List<Booking> findByBookingDateBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByBookingDateBetween(start, end);
    }

    @Override
    public boolean existsByBookingNumber(String bookingNumber) {
        return repository.existsByBookingNumber(bookingNumber);
    }
}
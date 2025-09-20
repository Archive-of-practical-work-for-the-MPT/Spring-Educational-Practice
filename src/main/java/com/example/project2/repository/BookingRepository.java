package com.example.project2.repository;

import com.example.project2.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingNumber(String bookingNumber);
    List<Booking> findByPassengerId(Long passengerId);
    List<Booking> findByFlightId(Long flightId);
    List<Booking> findByBookingDateBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByBookingNumber(String bookingNumber);
}
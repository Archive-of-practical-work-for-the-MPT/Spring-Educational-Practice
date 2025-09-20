package com.example.project2.repository;

import com.example.project2.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByEmail(String email);
    Optional<Passenger> findByPassportNumber(String passportNumber);
    boolean existsByEmail(String email);
    boolean existsByPassportNumber(String passportNumber);
    boolean existsByPhone(String phone);
}
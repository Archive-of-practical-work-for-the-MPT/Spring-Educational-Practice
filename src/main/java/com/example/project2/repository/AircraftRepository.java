package com.example.project2.repository;

import com.example.project2.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    Optional<Aircraft> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}
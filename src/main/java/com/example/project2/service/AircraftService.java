package com.example.project2.service;

import com.example.project2.model.Aircraft;

import java.util.List;

public interface AircraftService {
    List<Aircraft> findAllAircrafts();
    Aircraft createAircraft(Aircraft aircraft);
    Aircraft updateAircraft(Aircraft aircraft);
    Aircraft findAircraftById(Long id);
    void deleteAircraft(Long id);
    Aircraft findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}
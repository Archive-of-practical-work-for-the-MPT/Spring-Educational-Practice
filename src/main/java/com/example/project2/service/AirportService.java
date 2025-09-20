package com.example.project2.service;

import com.example.project2.model.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAllAirports();
    Airport createAirport(Airport airport);
    Airport updateAirport(Airport airport);
    Airport findAirportById(Long id);
    void deleteAirport(Long id);
    Airport findByCode(String code);
    boolean existsByCode(String code);
}
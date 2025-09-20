package com.example.project2.service.impl;

import com.example.project2.model.Airport;
import com.example.project2.repository.AirportRepository;
import com.example.project2.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository repository;

    public AirportServiceImpl(AirportRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Airport> findAllAirports() {
        return repository.findAll();
    }

    @Override
    public Airport createAirport(Airport airport) {
        return repository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport airport) {
        return repository.save(airport);
    }

    @Override
    public Airport findAirportById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteAirport(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Airport findByCode(String code) {
        return repository.findByCode(code).orElse(null);
    }

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }
}
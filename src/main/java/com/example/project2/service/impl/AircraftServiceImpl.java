package com.example.project2.service.impl;

import com.example.project2.model.Aircraft;
import com.example.project2.repository.AircraftRepository;
import com.example.project2.service.AircraftService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository repository;

    public AircraftServiceImpl(AircraftRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Aircraft> findAllAircrafts() {
        return repository.findAll();
    }

    @Override
    public Aircraft createAircraft(Aircraft aircraft) {
        return repository.save(aircraft);
    }

    @Override
    public Aircraft updateAircraft(Aircraft aircraft) {
        return repository.save(aircraft);
    }

    @Override
    public Aircraft findAircraftById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteAircraft(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Aircraft findBySerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber).orElse(null);
    }

    @Override
    public boolean existsBySerialNumber(String serialNumber) {
        return repository.existsBySerialNumber(serialNumber);
    }
}
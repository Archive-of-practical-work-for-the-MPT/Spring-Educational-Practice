package com.example.project2.service.impl;

import com.example.project2.model.Passenger;
import com.example.project2.repository.PassengerRepository;
import com.example.project2.service.PassengerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository repository;

    public PassengerServiceImpl(PassengerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Passenger> findAllPassengers() {
        return repository.findAll();
    }

    @Override
    public Passenger createPassenger(Passenger passenger) {
        return repository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Passenger passenger) {
        return repository.save(passenger);
    }

    @Override
    public Passenger findPassengerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deletePassenger(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Passenger findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public Passenger findByPassportNumber(String passportNumber) {
        return repository.findByPassportNumber(passportNumber).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByPassportNumber(String passportNumber) {
        return repository.existsByPassportNumber(passportNumber);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }
}
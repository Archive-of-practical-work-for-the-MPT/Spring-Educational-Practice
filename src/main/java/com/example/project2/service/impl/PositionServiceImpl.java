package com.example.project2.service.impl;

import com.example.project2.model.Position;
import com.example.project2.repository.PositionRepository;
import com.example.project2.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;

    public PositionServiceImpl(PositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Position> findAllPositions() {
        return repository.findAll();
    }

    @Override
    public Position createPosition(Position position) {
        return repository.save(position);
    }

    @Override
    public Position updatePosition(Position position) {
        return repository.save(position);
    }

    @Override
    public Position findPositionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deletePosition(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Position findByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
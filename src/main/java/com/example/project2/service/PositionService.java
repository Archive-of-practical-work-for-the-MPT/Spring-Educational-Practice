package com.example.project2.service;

import com.example.project2.model.Position;

import java.util.List;

public interface PositionService {
    List<Position> findAllPositions();
    Position createPosition(Position position);
    Position updatePosition(Position position);
    Position findPositionById(Long id);
    void deletePosition(Long id);
    Position findByName(String name);
    boolean existsByName(String name);
}
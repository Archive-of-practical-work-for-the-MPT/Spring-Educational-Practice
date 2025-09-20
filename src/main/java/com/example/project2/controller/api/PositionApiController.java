package com.example.project2.controller.api;

import com.example.project2.model.Position;
import com.example.project2.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionApiController {

    private final PositionService positionService;

    @Autowired
    public PositionApiController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getAllPositions() {
        return positionService.findAllPositions();
    }

    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Long id) {
        return positionService.findPositionById(id);
    }

    @PostMapping
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }

    @PutMapping("/{id}")
    public Position updatePosition(@PathVariable Long id, @RequestBody Position position) {
        position.setId(id);
        return positionService.updatePosition(position);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }
}
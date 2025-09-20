package com.example.project2.controller.web;

import com.example.project2.model.Position;
import com.example.project2.service.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public String getAllPositions(Model model) {
        List<Position> positions = positionService.findAllPositions();
        model.addAttribute("positions", positions);
        model.addAttribute("position", new Position());
        return "positions/positions";
    }

    @PostMapping
    public String addPosition(@Valid @ModelAttribute Position position, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Position> positions = positionService.findAllPositions();
            model.addAttribute("positions", positions);
            model.addAttribute("position", position);
            return "positions/positions";
        }
        positionService.createPosition(position);
        return "redirect:/positions";
    }

    @GetMapping("/edit/{id}")
    public String editPosition(@PathVariable Long id, Model model) {
        Position position = positionService.findPositionById(id);
        model.addAttribute("position", position);
        return "positions/editPosition";
    }

    @PostMapping("/update")
    public String updatePosition(@Valid @ModelAttribute Position position, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Position> positions = positionService.findAllPositions();
            model.addAttribute("positions", positions);
            model.addAttribute("position", position);
            return "positions/positions";
        }
        positionService.updatePosition(position);
        return "redirect:/positions";
    }

    @GetMapping("/delete/{id}")
    public String deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return "redirect:/positions";
    }
}
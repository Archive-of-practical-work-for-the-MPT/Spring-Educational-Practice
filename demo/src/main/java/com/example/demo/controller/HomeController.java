package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Главный контроллер для отображения домашней страницы
@Controller
public class HomeController {

    // Отображение домашней страницы
    @GetMapping("/")
    public String home() {
        return "home";
    }
}

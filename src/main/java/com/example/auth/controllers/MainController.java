package com.example.auth.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

// Главный контроллер для обработки основных страниц приложения
@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            
            // Определяем роль пользователя и перенаправляем на соответствующую панель
            for (GrantedAuthority authority : authorities) {
                switch (authority.getAuthority()) {
                    case "ADMIN":
                        return "redirect:/admin";
                    case "EMPLOYEE":
                        return "redirect:/employee";
                    case "VISITOR":
                        return "redirect:/visitor";
                }
            }
        }
        
        // Если пользователь не авторизован, показываем главную страницу
        return "index";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
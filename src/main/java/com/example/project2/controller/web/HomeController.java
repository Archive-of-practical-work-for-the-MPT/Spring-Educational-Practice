package com.example.project2.controller.web;

import com.example.project2.util.RoleUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
            
            if (RoleUtils.isAdmin(authentication)) {
                model.addAttribute("userRole", "Администратор");
            } else if (RoleUtils.isEmployee(authentication)) {
                model.addAttribute("userRole", "Сотрудник");
            } else if (RoleUtils.isPassenger(authentication)) {
                model.addAttribute("userRole", "Пассажир");
            } else {
                model.addAttribute("userRole", "Пользователь");
            }
        } else {
            // If user is not authenticated, redirect to login page
            return "redirect:/login";
        }
        
        return "index";
    }
    
    // Also handle the root path
    @GetMapping("/")
    public String root(Authentication authentication) {
        // If user is authenticated, redirect to home, otherwise to login
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }
}
package com.example.auth.controllers;

import com.example.auth.models.User;
import com.example.auth.models.RoleEnum;
import com.example.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

// Контроллер для функционала администратора океанариума
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/index";
    }
    
    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("user_list", userService.getAllUsers());
        return "admin/users";
    }
    
    @GetMapping("/user/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("Неверный ID пользователя: " + id);
        }
        model.addAttribute("user_object", user);
        model.addAttribute("roles", RoleEnum.values());
        return "admin/user_detail";
    }
    
    @PostMapping("/user/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String username,
                             @RequestParam(name = "roles[]", required = false) String[] roles) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("Неверный ID пользователя: " + id);
        }
        user.setUsername(username);
        
        user.getRoles().clear();
        if (roles != null) {
            for (String role : roles) {
                user.getRoles().add(RoleEnum.valueOf(role));
            }
        }
        
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
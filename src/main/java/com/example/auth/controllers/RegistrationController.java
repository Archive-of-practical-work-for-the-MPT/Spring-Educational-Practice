package com.example.auth.controllers;

import com.example.auth.models.User;
import com.example.auth.models.RoleEnum;
import com.example.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.regex.Pattern;

// Контроллер для регистрации пользователей в системе океанариума
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String regView() {
        return "regis";
    }

    @PostMapping("/registration")
    public String reg(@RequestParam String username, 
                      @RequestParam String password, 
                      @RequestParam String confirmPassword,
                      Model model) {
        
        // Проверка на существование пользователя
        if (userService.existsByUsername(username)) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        
        // Проверка на совпадение паролей
        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Пароли не совпадают");
            return "regis";
        }
        
        // Валидация пароля
        if (!isValidPassword(password)) {
            model.addAttribute("message", "Пароль должен содержать минимум 8 символов, " +
                    "включая заглавные и строчные буквы, цифры и специальные символы");
            return "regis";
        }
        
        // Создание нового пользователя с ролью посетителя по умолчанию
        User user = new User();
        user.setUsername(username);
        user.setActive(true);
        user.setRoles(Collections.singleton(RoleEnum.VISITOR));
        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);
        
        return "redirect:/login";
    }
    
    // Проверка валидности пароля
    private boolean isValidPassword(String password) {
        // Минимум 8 символов
        if (password.length() < 8) {
            return false;
        }
        
        // Должен содержать хотя бы одну заглавную букву
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }
        
        // Должен содержать хотя бы одну строчную букву
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return false;
        }
        
        // Должен содержать хотя бы одну цифру
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }
        
        // Должен содержать хотя бы один специальный символ
        if (!Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(password).find()) {
            return false;
        }
        
        return true;
    }
}
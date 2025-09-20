package com.example.project2.controller.web;

import com.example.project2.dto.LoginRequest;
import com.example.project2.dto.RegistrationRequest;
import com.example.project2.model.User;
import com.example.project2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationRequest registrationRequest, 
                              BindingResult bindingResult, Model model) {
        
        // Проверка на ошибки валидации
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        // Проверка совпадения паролей
        if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
            model.addAttribute("passwordMismatch", "Пароли не совпадают");
            return "register";
        }
        
        // Проверка, существует ли пользователь с таким именем
        if (userService.existsByUsername(registrationRequest.getUsername())) {
            model.addAttribute("usernameExists", "Пользователь с таким именем уже существует");
            return "register";
        }
        
        // Создание нового пользователя
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword()); // Не кодируем пароль здесь, пусть UserService сам это сделает
        user.setRole(registrationRequest.getRole());
        
        userService.createUser(user);
        
        // Автоматический вход после регистрации
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    registrationRequest.getUsername(),
                    registrationRequest.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }
}
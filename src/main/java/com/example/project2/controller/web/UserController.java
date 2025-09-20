package com.example.project2.controller.web;

import com.example.project2.model.User;
import com.example.project2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "users/users";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<User> users = userService.findAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("user", user);
            return "users/users";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        // Убираем пароль из модели перед отправкой в представление
        if (user != null) {
            user.setPassword(null);
        }
        model.addAttribute("user", user);
        return "users/editUser";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<User> users = userService.findAllUsers();
            model.addAttribute("users", users);
            // Убираем пароль из модели перед отправкой в представление
            user.setPassword(null);
            model.addAttribute("user", user);
            return "users/users";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
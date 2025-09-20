package com.example.project2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {
    
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    private String username;
    
    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;
    
    @NotBlank(message = "Подтверждение пароля не должно быть пустым")
    private String confirmPassword;
    
    @NotBlank(message = "Роль не должна быть пустой")
    @Size(min = 2, max = 20, message = "Роль должна быть от 2 до 20 символов")
    private String role;

    // Constructors
    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String password, String confirmPassword, String role) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
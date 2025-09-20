package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Имя пассажира не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя пассажира должно быть от 2 до 50 символов")
    private String firstName;
    
    @NotBlank(message = "Фамилия пассажира не должна быть пустой")
    @Size(min = 2, max = 50, message = "Фамилия пассажира должна быть от 2 до 50 символов")
    private String lastName;
    
    @NotBlank(message = "Номер паспорта не должен быть пустым")
    @Size(min = 5, max = 20, message = "Номер паспорта должен быть от 5 до 20 символов")
    private String passportNumber;
    
    @NotNull(message = "Дата рождения не должна быть пустой")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;
    
    @NotBlank(message = "Email пассажира не должен быть пустым")
    @Size(min = 5, max = 100, message = "Email пассажира должен быть от 5 до 100 символов")
    private String email;
    
    @NotBlank(message = "Телефон пассажира не должен быть пустым")
    @Size(min = 10, max = 20, message = "Телефон пассажира должен быть от 10 до 20 символов")
    private String phone;
    
    // Геттеры и сеттеры
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassportNumber() {
        return passportNumber;
    }
    
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
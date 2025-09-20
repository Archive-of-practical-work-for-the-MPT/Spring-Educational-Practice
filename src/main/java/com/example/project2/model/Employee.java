package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Имя сотрудника не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя сотрудника должно быть от 2 до 50 символов")
    private String firstName;
    
    @NotBlank(message = "Фамилия сотрудника не должна быть пустой")
    @Size(min = 2, max = 50, message = "Фамилия сотрудника должна быть от 2 до 50 символов")
    private String lastName;
    
    @NotBlank(message = "Email сотрудника не должен быть пустым")
    @Size(min = 5, max = 100, message = "Email сотрудника должен быть от 5 до 100 символов")
    private String email;
    
    @NotNull(message = "Дата рождения не должна быть пустой")
    private LocalDate birthDate;
    
    @NotNull(message = "Должность не должна быть пустой")
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    
    @NotBlank(message = "Телефон сотрудника не должен быть пустым")
    @Size(min = 10, max = 20, message = "Телефон сотрудника должен быть от 10 до 20 символов")
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
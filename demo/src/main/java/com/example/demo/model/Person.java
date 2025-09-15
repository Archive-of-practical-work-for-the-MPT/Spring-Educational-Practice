package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

// Модель Person для демонстрации связи один-к-одному с Passport
@Entity
@Table(name = "persons")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Имя не должно быть пустым")
    @Size(max = 100, message = "Имя не должно превышать 100 символов")
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Email(message = "Некорректный формат email")
    @NotBlank(message = "Email не должен быть пустым")
    @Size(max = 100, message = "Email не должен превышать 100 символов")
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    
    @Min(value = 0, message = "Возраст должен быть не менее 0")
    @Max(value = 150, message = "Возраст должен быть не более 150")
    @Column(name = "age")
    private Integer age;
    
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Passport passport;
    
    // Конструкторы
    public Person() {}
    
    public Person(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Passport getPassport() {
        return passport;
    }
    
    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
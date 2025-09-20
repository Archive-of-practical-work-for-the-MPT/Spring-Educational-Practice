package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Название аэропорта не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название аэропорта должно быть от 2 до 100 символов")
    private String name;
    
    @NotBlank(message = "Код аэропорта не должен быть пустым")
    @Size(min = 3, max = 3, message = "Код аэропорта должен состоять из 3 символов")
    private String code;
    
    @NotBlank(message = "Город аэропорта не должен быть пустым")
    @Size(min = 2, max = 50, message = "Город аэропорта должен быть от 2 до 50 символов")
    private String city;
    
    @NotBlank(message = "Страна аэропорта не должна быть пустой")
    @Size(min = 2, max = 50, message = "Страна аэропорта должна быть от 2 до 50 символов")
    private String country;
    
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
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
}
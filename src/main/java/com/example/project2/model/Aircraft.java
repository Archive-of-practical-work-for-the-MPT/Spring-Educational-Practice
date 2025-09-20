package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Модель самолета не должна быть пустой")
    @Size(min = 2, max = 50, message = "Модель самолета должна быть от 2 до 50 символов")
    private String model;
    
    @NotBlank(message = "Номер самолета не должен быть пустым")
    @Size(min = 2, max = 20, message = "Номер самолета должен быть от 2 до 20 символов")
    private String serialNumber;
    
    @NotNull(message = "Вместимость не должна быть пустой")
    @Positive(message = "Вместимость должна быть положительным числом")
    private Integer capacity;
    
    @NotBlank(message = "Производитель не должен быть пустым")
    @Size(min = 2, max = 50, message = "Производитель должен быть от 2 до 50 символов")
    private String manufacturer;
    
    // Геттеры и сеттеры
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public Integer getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
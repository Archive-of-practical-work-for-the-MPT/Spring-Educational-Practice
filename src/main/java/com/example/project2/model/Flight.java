package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Номер рейса не должен быть пустым")
    @Size(min = 4, max = 10, message = "Номер рейса должен быть от 4 до 10 символов")
    private String flightNumber;
    
    @NotNull(message = "Аэропорт вылета не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    
    @NotNull(message = "Аэропорт прибытия не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
    
    @NotNull(message = "Самолет не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;
    
    @NotNull(message = "Время вылета не должно быть пустым")
    private LocalDateTime departureTime;
    
    @NotNull(message = "Время прибытия не должно быть пустым")
    private LocalDateTime arrivalTime;
    
    // Геттеры и сеттеры
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }
    
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public Airport getDepartureAirport() {
        return departureAirport;
    }
    
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }
    
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }
    
    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
    
    public Aircraft getAircraft() {
        return aircraft;
    }
    
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
    
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
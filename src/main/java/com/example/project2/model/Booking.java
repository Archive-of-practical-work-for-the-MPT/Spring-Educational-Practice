package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Номер бронирования не должен быть пустым")
    @Size(min = 5, max = 20, message = "Номер бронирования должен быть от 5 до 20 символов")
    private String bookingNumber;
    
    @NotNull(message = "Пассажир не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    
    @NotNull(message = "Рейс не должен быть пустым")
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    
    @NotNull(message = "Дата бронирования не должна быть пустой")
    private LocalDateTime bookingDate;
    
    @NotNull(message = "Стоимость не должна быть пустой")
    @Positive(message = "Стоимость должна быть положительным числом")
    private BigDecimal price;
    
    @NotBlank(message = "Статус бронирования не должен быть пустым")
    @Size(min = 2, max = 20, message = "Статус бронирования должен быть от 2 до 20 символов")
    private String status; // confirmed, cancelled, pending
    
    // Геттеры и сеттеры
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBookingNumber() {
        return bookingNumber;
    }
    
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    
    public Passenger getPassenger() {
        return passenger;
    }
    
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public Flight getFlight() {
        return flight;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
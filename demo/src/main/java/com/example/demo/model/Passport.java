package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// Модель Passport для демонстрации связи один-к-одному с Person
@Entity
@Table(name = "passports")
public class Passport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Серия паспорта не должна быть пустой")
    @Size(max = 10, message = "Серия паспорта не должна превышать 10 символов")
    @Column(name = "series", nullable = false, length = 10)
    private String series;
    
    @NotBlank(message = "Номер паспорта не должен быть пустым")
    @Size(max = 10, message = "Номер паспорта не должен превышать 10 символов")
    @Column(name = "number", nullable = false, length = 10)
    private String number;
    
    @NotNull(message = "Дата выдачи паспорта обязательна")
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;
    
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    
    // Конструкторы
    public Passport() {}
    
    public Passport(String series, String number, LocalDate issueDate) {
        this.series = series;
        this.number = number;
        this.issueDate = issueDate;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSeries() {
        return series;
    }
    
    public void setSeries(String series) {
        this.series = series;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public LocalDate getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    
    public Person getPerson() {
        return person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
}
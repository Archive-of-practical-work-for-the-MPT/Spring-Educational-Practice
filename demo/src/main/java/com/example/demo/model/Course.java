package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

// Модель Course для демонстрации связи многие-ко-многим с Student
@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Код курса не должен быть пустым")
    @Size(max = 10, message = "Код курса не должен превышать 10 символов")
    @Column(name = "code", unique = true, nullable = false, length = 10)
    private String code;
    
    @NotBlank(message = "Название курса не должно быть пустым")
    @Size(max = 100, message = "Название курса не должно превышать 100 символов")
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Min(value = 0, message = "Количество кредитов должно быть не менее 0")
    @Column(name = "credits")
    private Integer credits;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
    
    // Конструкторы
    public Course() {}
    
    public Course(String code, String name, Integer credits, String description) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCredits() {
        return credits;
    }
    
    public void setCredits(Integer credits) {
        this.credits = credits;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
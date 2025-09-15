package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Модель Student для демонстрации связи многие-ко-многим с Course
@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Имя студента не должно быть пустым")
    @Size(max = 50, message = "Имя студента не должно превышать 50 символов")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @NotBlank(message = "Фамилия студента не должна быть пустой")
    @Size(max = 50, message = "Фамилия студента не должна превышать 50 символов")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @PastOrPresent(message = "Дата зачисления должна быть в прошлом или сегодня")
    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;
    
    @DecimalMin(value = "0.0", message = "Средний балл должен быть не менее 0.0")
    @DecimalMax(value = "4.0", message = "Средний балл должен быть не более 4.0")
    @Column(name = "gpa")
    private Double gpa;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
    
    // Конструкторы
    public Student() {}
    
    public Student(String firstName, String lastName, LocalDate enrollmentDate, Double gpa) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentDate = enrollmentDate;
        this.gpa = gpa;
    }
    
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
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public Double getGpa() {
        return gpa;
    }
    
    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    // Вспомогательный метод для добавления курса студенту
    public void addCourse(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }
    
    // Вспомогательный метод для удаления курса у студента
    public void removeCourse(Course course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }
}
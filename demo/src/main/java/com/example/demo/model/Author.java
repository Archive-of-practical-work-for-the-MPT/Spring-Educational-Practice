package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Модель Author для демонстрации связи один-ко-многим с Book
@Entity
@Table(name = "authors")
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Имя автора не должно быть пустым")
    @Size(max = 50, message = "Имя автора не должно превышать 50 символов")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @NotBlank(message = "Фамилия автора не должна быть пустой")
    @Size(max = 50, message = "Фамилия автора не должна превышать 50 символов")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Past(message = "Дата рождения должна быть в прошлом")
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
    
    // Конструкторы
    public Author() {}
    
    public Author(String firstName, String lastName, LocalDate birthDate, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.biography = biography;
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
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getBiography() {
        return biography;
    }
    
    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    // Вспомогательный метод для добавления книги автору
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }
    
    // Вспомогательный метод для удаления книги у автора
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}
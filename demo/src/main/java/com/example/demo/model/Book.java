package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// Модель Book для демонстрации связи один-ко-многим с Author
@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Название книги не должно быть пустым")
    @Size(max = 200, message = "Название книги не должно превышать 200 символов")
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    
    @Size(max = 20, message = "ISBN не должен превышать 20 символов")
    @Column(name = "isbn", unique = true, length = 20)
    private String isbn;
    
    @PastOrPresent(message = "Дата публикации должна быть в прошлом или сегодня")
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    
    // Конструкторы
    public Book() {}
    
    public Book(String title, String isbn, LocalDate publicationDate) {
        this.title = title;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public void setAuthor(Author author) {
        this.author = author;
    }
}
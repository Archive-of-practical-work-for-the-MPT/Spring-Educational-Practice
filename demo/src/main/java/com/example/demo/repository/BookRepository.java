package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Репозиторий для работы с сущностью Book
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Поиск книг по названию
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Поиск книг по ISBN
    Book findByIsbn(String isbn);
    
    // Поиск книг по идентификатору автора
    List<Book> findByAuthorId(Long authorId);
    
    // Поиск книг по названию с пагинацией
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
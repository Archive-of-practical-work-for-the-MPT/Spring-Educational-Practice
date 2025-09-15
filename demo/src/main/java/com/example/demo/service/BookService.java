package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Сервис для работы с сущностью Book
@Service
public class BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    // Сохранение книги
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    // Получение всех книг
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // Получение книги по идентификатору
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    // Удаление книги по идентификатору
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    
    // Поиск книг по названию
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Поиск книги по ISBN
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    
    // Поиск книг по идентификатору автора  
    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    
    // Получение книг с пагинацией
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    
    // Поиск книг по названию с пагинацией
    public Page<Book> searchBooksByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
}
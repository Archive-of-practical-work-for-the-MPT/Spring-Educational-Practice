package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Сервис для работы с сущностью Author
@Service
public class AuthorService {
    
    private final AuthorRepository authorRepository;
    
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    // Сохранение автора
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    
    // Получение всех авторов
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    // Получение автора по идентификатору
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }
    
    // Удаление автора по идентификатору
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
    
    // Поиск авторов по фамилии
    public List<Author> getAuthorsByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }
    
    // Поиск авторов по имени и фамилии
    public List<Author> getAuthorsByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    
    // Получение авторов с пагинацией
    public Page<Author> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
    
    // Поиск авторов по фамилии с пагинацией
    public Page<Author> searchAuthorsByLastName(String lastName, Pageable pageable) {
        return authorRepository.findByLastNameContainingIgnoreCase(lastName, pageable);
    }
}
package com.example.demo.repository;

import com.example.demo.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Репозиторий для работы с сущностью Author
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    // Поиск авторов по фамилии
    List<Author> findByLastName(String lastName);
    
    // Поиск авторов по имени и фамилии
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Поиск авторов по фамилии с пагинацией
    Page<Author> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
}
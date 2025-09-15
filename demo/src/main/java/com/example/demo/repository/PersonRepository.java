package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Репозиторий для работы с сущностью Person
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    // Поиск персоны по email
    Optional<Person> findByEmail(String email);
    
    // Проверка существования персоны по email
    boolean existsByEmail(String email);
    
     // Поиск персон по имени с пагинацией
    Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Репозиторий для работы с сущностью Student
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Поиск студентов по фамилии
    List<Student> findByLastName(String lastName);
    
    // Поиск студентов по имени и фамилии
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Поиск студентов по фамилии с пагинацией
    Page<Student> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
}
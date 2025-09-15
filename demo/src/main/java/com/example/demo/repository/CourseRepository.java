package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Репозиторий для работы с сущностью Course
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    // Поиск курса по коду
    Optional<Course> findByCode(String code);
    
    // Поиск курсов по названию
    List<Course> findByNameContainingIgnoreCase(String name);
    
    // Проверка существования курса по коду
    boolean existsByCode(String code);
    
    // Поиск курсов по названию с пагинацией
    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
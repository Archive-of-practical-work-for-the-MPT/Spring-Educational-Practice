package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Сервис для работы с сущностью Course
@Service
public class CourseService {
    
    private final CourseRepository courseRepository;
    
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    // Сохранение курса
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    // Получение всех курсов
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    // Получение курса по идентификатору
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    
    // Удаление курса по идентификатору
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
    
    // Поиск курса по коду
    public Optional<Course> getCourseByCode(String code) {
        return courseRepository.findByCode(code);
    }
    
    // Поиск курсов по названию
    public List<Course> getCoursesByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Проверка существования курса по коду
    public boolean existsByCode(String code) {
        return courseRepository.existsByCode(code);
    }
    
    // Получение курсов с пагинацией
    public Page<Course> getCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
    
    // Поиск курсов по названию с пагинацией
    public Page<Course> searchCoursesByName(String name, Pageable pageable) {
        return courseRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
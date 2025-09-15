package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Сервис для работы с сущностью Student
@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    // Сохранение студента
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    
    // Получение всех студентов
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // Получение студента по идентификатору
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    
    // Удаление студента по идентификатору
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
    
    // Поиск студентов по фамилии
    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }
    
    // Поиск студентов по имени и фамилии
    public List<Student> getStudentsByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    
    // Получение студентов с пагинацией
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    
    // Поиск студентов по фамилии с пагинацией
    public Page<Student> searchStudentsByLastName(String lastName, Pageable pageable) {
        return studentRepository.findByLastNameContainingIgnoreCase(lastName, pageable);
    }
}
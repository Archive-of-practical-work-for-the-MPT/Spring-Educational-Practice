package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Контроллер для работы с сущностью Student
@Controller
@RequestMapping("/students")
public class StudentController {
    
    private final StudentService studentService;
    private final CourseService courseService;
    
    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
    
    // Отображение списка всех студентов с поддержкой поиска, пагинации, фильтрации и сортировки
    @GetMapping
    public String getAllStudents(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        
        // Определение направления сортировки
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        // Создание объекта Pageable для пагинации
        Pageable pageable = PageRequest.of(page, 5, sort);
        
        Page<Student> studentPage;
        
        // Если есть поисковый запрос, используем поиск, иначе получаем все записи
        if (search != null && !search.isEmpty()) {
            studentPage = studentService.searchStudentsByLastName(search, pageable);
        } else {
            studentPage = studentService.getStudents(pageable);
        }
        
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("totalItems", studentPage.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("courses", courseService.getAllCourses());
        
        // Для переключения направления сортировки
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        
        return "students/list";
    }
    
    // Отображение формы для создания нового студента
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.getAllCourses());
        return "students/create";
    }
    
    // Обработка создания нового студента
    @PostMapping
    public String createStudent(@Valid @ModelAttribute("student") Student student, 
                               BindingResult result, 
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("courses", courseService.getAllCourses());
            return "students/create";
        }
        
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    
    // Отображение формы для редактирования студента
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            model.addAttribute("courses", courseService.getAllCourses());
            return "students/edit";
        } else {
            return "redirect:/students";
        }
    }
    
    // Обработка обновления студента
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, 
                               @Valid @ModelAttribute("student") Student student, 
                               BindingResult result, 
                               Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            model.addAttribute("courses", courseService.getAllCourses());
            return "students/edit";
        }
        
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    
    // Удаление студента
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
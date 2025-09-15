package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
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

// Контроллер для работы с сущностью Course
@Controller
@RequestMapping("/courses")
public class CourseController {
    
    private final CourseService courseService;
    private final StudentService studentService;
    
    @Autowired
    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }
    
    // Отображение списка всех курсов с поддержкой поиска, пагинации, фильтрации и сортировки
    @GetMapping
    public String getAllCourses(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        
        // Определение направления сортировки
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        // Создание объекта Pageable для пагинации
        Pageable pageable = PageRequest.of(page, 5, sort);
        
        Page<Course> coursePage;
        
        // Если есть поисковый запрос, используем поиск, иначе получаем все записи
        if (search != null && !search.isEmpty()) {
            coursePage = courseService.searchCoursesByName(search, pageable);
        } else {
            coursePage = courseService.getCourses(pageable);
        }
        
        model.addAttribute("courses", coursePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coursePage.getTotalPages());
        model.addAttribute("totalItems", coursePage.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("students", studentService.getAllStudents());
        
        // Для переключения направления сортировки
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        
        return "courses/list";
    }
    
    // Отображение формы для создания нового курса
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("students", studentService.getAllStudents());
        return "courses/create";
    }
    
    // Обработка создания нового курса
    @PostMapping
    public String createCourse(@Valid @ModelAttribute("course") Course course, 
                              BindingResult result, 
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            return "courses/create";
        }
        
        // Проверяем, существует ли уже курс с таким кодом
        if (courseService.existsByCode(course.getCode())) {
            model.addAttribute("errorMessage", "Курс с таким кодом уже существует");
            model.addAttribute("students", studentService.getAllStudents());
            return "courses/create";
        }
        
        courseService.saveCourse(course);
        return "redirect:/courses";
    }
    
    // Отображение формы для редактирования курса
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            model.addAttribute("course", course.get());
            model.addAttribute("students", studentService.getAllStudents());
            return "courses/edit";
        } else {
            return "redirect:/courses";
        }
    }
    
    // Обработка обновления курса
    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, 
                              @Valid @ModelAttribute("course") Course course, 
                              BindingResult result, 
                              Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            model.addAttribute("students", studentService.getAllStudents());
            return "courses/edit";
        }
        
        courseService.saveCourse(course);
        return "redirect:/courses";
    }
    
    // Удаление курса
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
}
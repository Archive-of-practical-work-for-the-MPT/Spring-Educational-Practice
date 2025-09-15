package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
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

// Контроллер для работы с сущностью Author
@Controller
@RequestMapping("/authors")
public class AuthorController {
    
    private final AuthorService authorService;
    
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    
    // Отображение списка всех авторов с поддержкой поиска, пагинации, фильтрации и сортировки
    @GetMapping
    public String getAllAuthors(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        
        // Определение направления сортировки
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        // Создание объекта Pageable для пагинации
        Pageable pageable = PageRequest.of(page, 5, sort);
        
        Page<Author> authorPage;
        
        // Если есть поисковый запрос, используем поиск, иначе получаем все записи
        if (search != null && !search.isEmpty()) {
            authorPage = authorService.searchAuthorsByLastName(search, pageable);
        } else {
            authorPage = authorService.getAuthors(pageable);
        }
        
        model.addAttribute("authors", authorPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", authorPage.getTotalPages());
        model.addAttribute("totalItems", authorPage.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        // Для переключения направления сортировки
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        
        return "authors/list";
    }
    
    // Отображение формы для создания нового автора 
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/create";
    }
    
    // Обработка создания нового автора
    @PostMapping
    public String createAuthor(@Valid @ModelAttribute("author") Author author, 
                              BindingResult result, 
                              Model model) {
        if (result.hasErrors()) {
            return "authors/create";
        }
        
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }
    
    // Отображение формы для редактирования автора
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            return "authors/edit";
        } else {
            return "redirect:/authors";
        }
    }
    
    // Обработка обновления автора
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") Long id, 
                              @Valid @ModelAttribute("author") Author author, 
                              BindingResult result, 
                              Model model) {
        if (result.hasErrors()) {
            author.setId(id);
            return "authors/edit";
        }
        
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }
    
    // Удаление автора
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/authors";
    }
}
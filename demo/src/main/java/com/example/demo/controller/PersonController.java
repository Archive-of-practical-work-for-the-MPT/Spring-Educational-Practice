package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.service.PassportService;
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

// Контроллер для работы с сущностью Person
@Controller
@RequestMapping("/persons")
public class PersonController {
    
    private final PersonService personService;
    private final PassportService passportService;
    
    @Autowired
    public PersonController(PersonService personService, PassportService passportService) {
        this.personService = personService;
        this.passportService = passportService;
    }
    
    // Отображение списка всех персон с поддержкой поиска, пагинации, фильтрации и сортировки
    @GetMapping
    public String getAllPersons(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        
        // Определение направления сортировки
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        // Создание объекта Pageable для пагинации
        Pageable pageable = PageRequest.of(page, 5, sort);
        
        Page<Person> personPage;
        
        // Если есть поисковый запрос, используем поиск, иначе получаем все записи
        if (search != null && !search.isEmpty()) {
            personPage = personService.searchPersonsByName(search, pageable);
        } else {
            personPage = personService.getPersons(pageable);
        }
        
        model.addAttribute("persons", personPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", personPage.getTotalPages());
        model.addAttribute("totalItems", personPage.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        // Для переключения направления сортировки
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        
        return "persons/list";
    }
    
    // Отображение формы для создания новой персоны
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("person", new Person());
        return "persons/create";
    }
    
    // Обработка создания новой персоны
    @PostMapping
    public String createPerson(@Valid @ModelAttribute("person") Person person, 
                              BindingResult result, 
                              Model model) {
        if (result.hasErrors()) {
            return "persons/create";
        }
        
        if (personService.existsByEmail(person.getEmail())) {
            model.addAttribute("errorMessage", "Пользователь с таким email уже существует");
            return "persons/create";
        }
        
        personService.savePerson(person);
        return "redirect:/persons";
    }
    
    // Отображение формы для редактирования персоны
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Person> person = personService.getPersonById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "persons/edit";
        } else {
            return "redirect:/persons";
        }
    }
    
    // Обработка обновления персоны
    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") Long id, 
                              @Valid @ModelAttribute("person") Person person, 
                              BindingResult result, 
                              Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "persons/edit";
        }
        
        personService.savePerson(person);
        return "redirect:/persons";
    }
    
    // Удаление персоны
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePersonById(id);
        return "redirect:/persons";
    }
}
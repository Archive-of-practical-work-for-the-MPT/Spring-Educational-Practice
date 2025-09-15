package com.example.demo.controller;

import com.example.demo.model.Passport;
import com.example.demo.service.PassportService;
import com.example.demo.service.PersonService;
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

// Контроллер для работы с сущностью Passport
@Controller
@RequestMapping("/passports")
public class PassportController {
    
    private final PassportService passportService;
    private final PersonService personService;
    
    @Autowired
    public PassportController(PassportService passportService, PersonService personService) {
        this.passportService = passportService;
        this.personService = personService;
    }
    
    // Отображение списка всех паспортов с поддержкой поиска, пагинации, фильтрации и сортировки
    @GetMapping
    public String getAllPassports(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        
        // Определение направления сортировки
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        // Создание объекта Pageable для пагинации
        Pageable pageable = PageRequest.of(page, 5, sort);
        
        Page<Passport> passportPage;
        
        // Если есть поисковый запрос, используем поиск, иначе получаем все записи
        if (search != null && !search.isEmpty()) {
            passportPage = passportService.searchPassportsBySeries(search, pageable);
        } else {
            passportPage = passportService.getPassports(pageable);
        }
        
        model.addAttribute("passports", passportPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", passportPage.getTotalPages());
        model.addAttribute("totalItems", passportPage.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("persons", personService.getAllPersons());
        
        // Для переключения направления сортировки
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
        
        return "passports/list";
    }
    
    // Отображение формы для создания нового паспорта
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("passport", new Passport());
        model.addAttribute("persons", personService.getAllPersons());
        return "passports/create";
    }
    
    // Обработка создания нового паспорта
    @PostMapping
    public String createPassport(@Valid @ModelAttribute("passport") Passport passport, 
                                BindingResult result, 
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("persons", personService.getAllPersons());
            return "passports/create";
        }
        
        // Проверяем, существует ли уже паспорт с такой серией и номером
        Passport existingPassport = passportService.findBySeriesAndNumber(
            passport.getSeries(), passport.getNumber());
        if (existingPassport != null) {
            model.addAttribute("errorMessage", "Паспорт с такой серией и номером уже существует");
            model.addAttribute("persons", personService.getAllPersons());
            return "passports/create";
        }
        
        passportService.savePassport(passport);
        return "redirect:/passports";
    }
    
    // Отображение формы для редактирования паспорта
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Passport> passport = passportService.getPassportById(id);
        if (passport.isPresent()) {
            model.addAttribute("passport", passport.get());
            model.addAttribute("persons", personService.getAllPersons());
            return "passports/edit";
        } else {
            return "redirect:/passports";
        }
    }
    
    // Обработка обновления паспорта
    @PostMapping("/update/{id}")
    public String updatePassport(@PathVariable("id") Long id, 
                                @Valid @ModelAttribute("passport") Passport passport, 
                                BindingResult result, 
                                Model model) {
        if (result.hasErrors()) {
            passport.setId(id);
            model.addAttribute("persons", personService.getAllPersons());
            return "passports/edit";
        }
        
        passportService.savePassport(passport);
        return "redirect:/passports";
    }
    
    // Удаление паспорта
    @GetMapping("/delete/{id}")
    public String deletePassport(@PathVariable("id") Long id) {
        passportService.deletePassportById(id);
        return "redirect:/passports";
    }
}
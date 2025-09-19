package com.example.auth.controllers;

import com.example.auth.models.Animal;
import com.example.auth.models.Show;
import com.example.auth.models.Ticket;
import com.example.auth.services.AnimalService;
import com.example.auth.services.ShowService;
import com.example.auth.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

// Контроллер для функционала сотрудников океанариума
@Controller
@RequestMapping("/employee")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class EmployeeController {

    @Autowired
    private AnimalService animalService;
    
    @Autowired
    private ShowService showService;
    
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String employeePanel(Model model) {
        return "employee/index";
    }
    
    @GetMapping("/animals")
    public String animals(Model model) {
        // Получаем всех животных из базы данных
        Iterable<Animal> animals = animalService.getAllAnimals();
        model.addAttribute("animals", animals);
        return "employee/animals";
    }
    
    @GetMapping("/schedule")
    public String schedule(Model model) {
        // Получаем все шоу из базы данных
        Iterable<Show> shows = showService.getAllShows();
        model.addAttribute("shows", shows);
        return "employee/schedule";
    }
    
    @GetMapping("/tickets")
    public String tickets(Model model) {
        // Получаем все билеты из базы данных
        Iterable<Ticket> ticketsIterable = ticketService.getAllTickets();
        List<Ticket> allTickets = new ArrayList<>();
        
        // Преобразуем Iterable в List
        for (Ticket ticket : ticketsIterable) {
            allTickets.add(ticket);
        }
        
        // Рассчитываем статистику
        int totalTickets = allTickets.size();
        double totalRevenue = 0.0;
        
        for (Ticket ticket : allTickets) {
            if (ticket.getPrice() != null) {
                totalRevenue += ticket.getPrice().doubleValue();
            }
        }
        
        // Передаем данные в модель
        model.addAttribute("allTickets", allTickets);
        model.addAttribute("totalTickets", totalTickets);
        model.addAttribute("totalRevenue", totalRevenue);
        
        return "employee/tickets";
    }
    
    // Добавить новое животное
    @PostMapping("/animals/add")
    public String addAnimal(@RequestParam String name, 
                           @RequestParam String species, 
                           @RequestParam String habitat, 
                           @RequestParam(required = false) String description,
                           RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Добавление животного: " + name + ", " + species + ", " + habitat);
            Animal animal = new Animal(name, species, description, habitat);
            animalService.saveAnimal(animal);
            System.out.println("Животное успешно добавлено с ID: " + animal.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Животное успешно добавлено!");
        } catch (Exception e) {
            System.err.println("Ошибка при добавлении животного: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при добавлении животного: " + e.getMessage());
        }
        return "redirect:/employee/animals";
    }
    
    /**
     * Обновить существующее животное
     */
    @PostMapping("/animals/update")
    public String updateAnimal(@RequestParam Long id,
                              @RequestParam String name, 
                              @RequestParam String species, 
                              @RequestParam String habitat, 
                              @RequestParam(required = false) String description) {
        System.out.println("Обновление животного с ID: " + id + ", новое имя: " + name);
        Animal animal = animalService.getAnimalById(id);
        if (animal != null) {
            animal.setName(name);
            animal.setSpecies(species);
            animal.setHabitat(habitat);
            animal.setDescription(description);
            animalService.saveAnimal(animal);
            System.out.println("Животное успешно обновлено");
        } else {
            System.out.println("Животное с ID " + id + " не найдено");
        }
        return "redirect:/employee/animals";
    }
    
    // Удалить животное
    @PostMapping("/animals/delete")
    public String deleteAnimal(@RequestParam Long id) {
        System.out.println("Удаление животного с ID: " + id);
        animalService.deleteAnimal(id);
        System.out.println("Животное успешно удалено");
        return "redirect:/employee/animals";
    }
}
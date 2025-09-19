package com.example.auth.controllers;

import com.example.auth.models.Animal;
import com.example.auth.models.Show;
import com.example.auth.models.Ticket;
import com.example.auth.models.User;
import com.example.auth.services.AnimalService;
import com.example.auth.services.ShowService;
import com.example.auth.services.TicketService;
import com.example.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Контроллер для функционала посетителей океанариума
@Controller
@RequestMapping("/visitor")
@PreAuthorize("hasAnyAuthority('VISITOR')")
public class VisitorController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private AnimalService animalService;
    
    @Autowired
    private ShowService showService;
    
    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public String visitorPanel(Model model) {
        return "visitor/index";
    }
    
    @GetMapping("/exhibits")
    public String exhibits(Model model) {
        // Получаем всех животных из базы данных
        Iterable<Animal> animals = animalService.getAllAnimals();
        model.addAttribute("animals", animals);
        return "visitor/exhibits";
    }
    
    @GetMapping("/shows")
    public String shows(Model model) {
        // Получаем все шоу из базы данных
        Iterable<Show> shows = showService.getAllShows();
        model.addAttribute("shows", shows);
        return "visitor/shows";
    }
    
    @GetMapping("/tickets")
    public String tickets(Model model, Authentication authentication) {
        try {
            logger.info("Loading tickets page");
            
            // Получаем все шоу из базы данных для покупки билетов
            Iterable<Show> shows = showService.getAllShows();
            model.addAttribute("shows", shows);
            logger.info("Loaded {} shows", ((List<Show>) shows).size());
            
            // Получаем имя текущего пользователя из аутентификации
            String username = authentication.getName();
            logger.info("Current username: {}", username);
            
            // Получаем полного объекта пользователя из сервиса
            User currentUser = userService.findByUsername(username);
            if (currentUser == null) {
                logger.warn("User not found: {}", username);
                model.addAttribute("error", "Пользователь не найден");
                return "visitor/tickets";
            }
            
            logger.info("Current user: {} with ID: {}", currentUser.getUsername(), currentUser.getId());
            
            // Получаем билеты текущего пользователя
            List<Ticket> userTickets = ticketService.getTicketsByVisitor(currentUser);
            model.addAttribute("tickets", userTickets);
            logger.info("Loaded {} tickets for user", userTickets.size());
            
            return "visitor/tickets";
        } catch (Exception e) {
            // Логируем ошибку для отладки
            logger.error("Error loading tickets", e);
            model.addAttribute("error", "Произошла ошибка при загрузке билетов: " + e.getMessage());
            return "visitor/tickets";
        }
    }
    
    @PostMapping("/tickets/buy")
    public String buyTicket(@RequestParam("showId") Long showId, 
                           @RequestParam(value = "ticketCount", defaultValue = "1") int ticketCount,
                           Authentication authentication, 
                           Model model) {
        try {
            logger.info("Buying {} tickets for show ID: {}", ticketCount, showId);
            
            // Получаем имя текущего пользователя из аутентификации
            String username = authentication.getName();
            logger.info("Current username: {}", username);
            
            // Получаем полного объекта пользователя из сервиса
            User currentUser = userService.findByUsername(username);
            if (currentUser == null) {
                logger.warn("User not found: {}", username);
                model.addAttribute("error", "Пользователь не найден");
                return tickets(model, authentication);
            }
            
            logger.info("Current user: {} with ID: {}", currentUser.getUsername(), currentUser.getId());
            
            // Получаем выбранное шоу
            Show selectedShow = showService.getShowById(showId);
            
            if (selectedShow == null) {
                logger.warn("Show with ID {} not found", showId);
                model.addAttribute("error", "Выбранное шоу не найдено");
                return tickets(model, authentication);
            }
            
            logger.info("Selected show: {}", selectedShow.getName());
            
            // Создаем билеты
            for (int i = 0; i < ticketCount; i++) {
                Ticket ticket = new Ticket();
                ticket.setVisitor(currentUser);
                ticket.setShow(selectedShow);
                ticket.setPurchaseDate(Timestamp.valueOf(LocalDateTime.now()));
                ticket.setPrice(new BigDecimal("500.00")); // Цена билета
                Ticket savedTicket = ticketService.saveTicket(ticket);
                logger.info("Saved ticket with ID: {}", savedTicket.getId());
            }
            
            // Перенаправляем обратно на страницу билетов
            return "redirect:/visitor/tickets";
        } catch (Exception e) {
            logger.error("Error buying tickets", e);
            model.addAttribute("error", "Произошла ошибка при покупке билетов: " + e.getMessage());
            return tickets(model, authentication);
        }
    }
    
    @GetMapping("/contacts")
    public String contacts(Model model) {
        return "visitor/contacts";
    }
}
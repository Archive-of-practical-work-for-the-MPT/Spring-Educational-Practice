package com.example.auth.services;

import com.example.auth.models.Ticket;
import com.example.auth.models.User;
import com.example.auth.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Сервис для работы с билетами в океанариуме
@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    // Получить все билеты
    public Iterable<Ticket> getAllTickets() {
        try {
            return ticketRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all tickets", e);
            return new ArrayList<>();
        }
    }

    // Получить билет по ID
    public Ticket getTicketById(Long id) {
        try {
            Optional<Ticket> ticket = ticketRepository.findById(id);
            return ticket.orElse(null);
        } catch (Exception e) {
            logger.error("Error fetching ticket by ID: " + id, e);
            return null;
        }
    }

    // Сохранить билет
    public Ticket saveTicket(Ticket ticket) {
        try {
            logger.info("Saving ticket for visitor ID: {} and show ID: {}", 
                       ticket.getVisitor() != null ? ticket.getVisitor().getId() : "null",
                       ticket.getShow() != null ? ticket.getShow().getId() : "null");
            return ticketRepository.save(ticket);
        } catch (Exception e) {
            logger.error("Error saving ticket", e);
            return null;
        }
    }

    // Удалить билет по ID
    public void deleteTicket(Long id) {
        try {
            ticketRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting ticket with ID: " + id, e);
        }
    }
    
    // Получить все билеты посетителя
    public List<Ticket> getTicketsByVisitor(User visitor) {
        try {
            if (visitor == null) {
                logger.warn("Visitor is null");
                return new ArrayList<>();
            }
            
            if (visitor.getId() == null) {
                logger.warn("Visitor ID is null");
                return new ArrayList<>();
            }
            
            logger.info("Fetching tickets for visitor ID: {}", visitor.getId());
            List<Ticket> tickets = ticketRepository.findByVisitorId(visitor.getId());
            logger.info("Found {} tickets for visitor ID: {}", tickets.size(), visitor.getId());
            return tickets;
        } catch (Exception e) {
            logger.error("Error fetching tickets for visitor ID: " + visitor.getId(), e);
            return new ArrayList<>();
        }
    }
}
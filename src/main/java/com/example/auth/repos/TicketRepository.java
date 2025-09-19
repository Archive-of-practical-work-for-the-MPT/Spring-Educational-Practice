package com.example.auth.repos;

import com.example.auth.models.Ticket;
import com.example.auth.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Репозиторий для работы с билетами в океанариуме
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByVisitorId(Long visitorId);
}
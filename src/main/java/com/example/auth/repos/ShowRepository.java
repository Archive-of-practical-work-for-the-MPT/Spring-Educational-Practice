package com.example.auth.repos;

import com.example.auth.models.Show;
import org.springframework.data.repository.CrudRepository;

// Репозиторий для работы с шоу в океанариуме
public interface ShowRepository extends CrudRepository<Show, Long> {
}
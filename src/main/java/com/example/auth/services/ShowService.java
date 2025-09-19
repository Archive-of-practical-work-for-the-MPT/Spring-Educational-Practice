package com.example.auth.services;

import com.example.auth.models.Show;
import com.example.auth.repos.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Сервис для работы с шоу в океанариуме
@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    // Получить все шоу
    public Iterable<Show> getAllShows() {
        return showRepository.findAll();
    }

    // Получить шоу по ID
    public Show getShowById(Long id) {
        Optional<Show> show = showRepository.findById(id);
        return show.orElse(null);
    }

    // Сохранить шоу
    public Show saveShow(Show show) {
        return showRepository.save(show);
    }

    // Удалить шоу по ID
    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}
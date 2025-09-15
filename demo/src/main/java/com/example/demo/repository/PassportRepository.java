package com.example.demo.repository;

import com.example.demo.model.Passport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Репозиторий для работы с сущностью Passport
@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
    
    // Поиск паспорта по серии и номеру
    Passport findBySeriesAndNumber(String series, String number);
    
    // Поиск паспортов по серии с пагинацией
    Page<Passport> findBySeriesContainingIgnoreCase(String series, Pageable pageable);
}
package com.example.demo.service;

import com.example.demo.model.Passport;
import com.example.demo.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Сервис для работы с сущностью Passport
@Service
public class PassportService {
    
    private final PassportRepository passportRepository;
    
    @Autowired
    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }
    
    // Сохранение паспорта
    public Passport savePassport(Passport passport) {
        return passportRepository.save(passport);
    }
    
    // Получение всех паспортов
    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }
    
    // Получение паспорта по идентификатору
    public Optional<Passport> getPassportById(Long id) {
        return passportRepository.findById(id);
    }
    
    // Удаление паспорта по идентификатору
    public void deletePassportById(Long id) {
        passportRepository.deleteById(id);
    }
    
    // Поиск паспорта по серии и номеру
    public Passport findBySeriesAndNumber(String series, String number) {
        return passportRepository.findBySeriesAndNumber(series, number);
    }
    
    // Получение паспортов с пагинацией
    public Page<Passport> getPassports(Pageable pageable) {
        return passportRepository.findAll(pageable);
    }
    
    // Поиск паспортов по серии с пагинацией
    public Page<Passport> searchPassportsBySeries(String series, Pageable pageable) {
        return passportRepository.findBySeriesContainingIgnoreCase(series, pageable);
    }
}
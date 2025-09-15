package com.example.demo.service;

import com.example.demo.model.Passport;
import com.example.demo.repository.PassportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для PassportService
 */
class PassportServiceTest {
    
    @Mock
    private PassportRepository passportRepository;
    
    @InjectMocks
    private PassportService passportService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testSavePassport() {
        // Подготовка
        Passport passport = new Passport("1234", "567890", LocalDate.now());
        when(passportRepository.save(passport)).thenReturn(passport);
        
        // Выполнение
        Passport savedPassport = passportService.savePassport(passport);
        
        // Проверка
        assertNotNull(savedPassport);
        assertEquals("1234", savedPassport.getSeries());
        assertEquals("567890", savedPassport.getNumber());
        assertNotNull(savedPassport.getIssueDate());
        verify(passportRepository, times(1)).save(passport);
    }
    
    @Test
    void testGetAllPassports() {
        // Подготовка
        Passport passport1 = new Passport("1234", "567890", LocalDate.now());
        Passport passport2 = new Passport("5678", "098765", LocalDate.now());
        List<Passport> passports = Arrays.asList(passport1, passport2);
        when(passportRepository.findAll()).thenReturn(passports);
        
        // Выполнение
        List<Passport> result = passportService.getAllPassports();
        
        // Проверка
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("1234", result.get(0).getSeries());
        assertEquals("5678", result.get(1).getSeries());
        verify(passportRepository, times(1)).findAll();
    }
    
    @Test
    void testGetPassportById() {
        // Подготовка
        Long id = 1L;
        Passport passport = new Passport("1234", "567890", LocalDate.now());
        passport.setId(id);
        when(passportRepository.findById(id)).thenReturn(Optional.of(passport));
        
        // Выполнение
        Optional<Passport> result = passportService.getPassportById(id);
        
        // Проверка
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("1234", result.get().getSeries());
        verify(passportRepository, times(1)).findById(id);
    }
    
    @Test
    void testDeletePassportById() {
        // Подготовка
        Long id = 1L;
        
        // Выполнение
        passportService.deletePassportById(id);
        
        // Проверка
        verify(passportRepository, times(1)).deleteById(id);
    }
    
    @Test
    void testFindBySeriesAndNumber() {
        // Подготовка
        String series = "1234";
        String number = "567890";
        Passport passport = new Passport(series, number, LocalDate.now());
        when(passportRepository.findBySeriesAndNumber(series, number)).thenReturn(passport);
        
        // Выполнение
        Passport result = passportService.findBySeriesAndNumber(series, number);
        
        // Проверка
        assertNotNull(result);
        assertEquals(series, result.getSeries());
        assertEquals(number, result.getNumber());
        verify(passportRepository, times(1)).findBySeriesAndNumber(series, number);
    }
}
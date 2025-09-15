package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для PersonService
 */
class PersonServiceTest {
    
    @Mock
    private PersonRepository personRepository;
    
    @InjectMocks
    private PersonService personService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testSavePerson() {
        // Подготовка
        Person person = new Person("Иван", "ivan@example.com", 25);
        when(personRepository.save(person)).thenReturn(person);
        
        // Выполнение
        Person savedPerson = personService.savePerson(person);
        
        // Проверка
        assertNotNull(savedPerson);
        assertEquals("Иван", savedPerson.getName());
        assertEquals("ivan@example.com", savedPerson.getEmail());
        assertEquals(25, savedPerson.getAge());
        verify(personRepository, times(1)).save(person);
    }
    
    @Test
    void testGetAllPersons() {
        // Подготовка
        Person person1 = new Person("Иван", "ivan@example.com", 25);
        Person person2 = new Person("Мария", "maria@example.com", 30);
        List<Person> persons = Arrays.asList(person1, person2);
        when(personRepository.findAll()).thenReturn(persons);
        
        // Выполнение
        List<Person> result = personService.getAllPersons();
        
        // Проверка
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Иван", result.get(0).getName());
        assertEquals("Мария", result.get(1).getName());
        verify(personRepository, times(1)).findAll();
    }
    
    @Test
    void testGetPersonById() {
        // Подготовка
        Long id = 1L;
        Person person = new Person("Иван", "ivan@example.com", 25);
        person.setId(id);
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        
        // Выполнение
        Optional<Person> result = personService.getPersonById(id);
        
        // Проверка
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("Иван", result.get().getName());
        verify(personRepository, times(1)).findById(id);
    }
    
    @Test
    void testDeletePersonById() {
        // Подготовка
        Long id = 1L;
        
        // Выполнение
        personService.deletePersonById(id);
        
        // Проверка
        verify(personRepository, times(1)).deleteById(id);
    }
    
    @Test
    void testExistsByEmail() {
        // Подготовка
        String email = "ivan@example.com";
        when(personRepository.existsByEmail(email)).thenReturn(true);
        
        // Выполнение
        boolean result = personService.existsByEmail(email);
        
        // Проверка
        assertTrue(result);
        verify(personRepository, times(1)).existsByEmail(email);
    }
}
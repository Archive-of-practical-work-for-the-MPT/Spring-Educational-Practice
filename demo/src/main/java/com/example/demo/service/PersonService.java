package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Сервис для работы с сущностью Person
@Service
public class PersonService {
    
    private final PersonRepository personRepository;
    
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    // Сохранение персоны
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
    
    // Получение всех персон
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    
     // Получение персоны по идентификатору
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }
    
    // Удаление персоны по идентификатору
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
    
    // Проверка существования персоны по email
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }
    
     // Получение персоны по email
    public Optional<Person> getPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }
    
     // Получение персон с пагинацией
    public Page<Person> getPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
    
    // Поиск персон по имени с пагинацией
    public Page<Person> searchPersonsByName(String name, Pageable pageable) {
        return personRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
package com.example.auth.services;

import com.example.auth.models.Animal;
import com.example.auth.repos.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Сервис для работы с животными в океанариуме
@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // Получить всех животных
    public Iterable<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // Получить животное по ID
    public Animal getAnimalById(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.orElse(null);
    }

    // Сохранить животное
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // Удалить животное по ID
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
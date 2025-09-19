package com.example.auth.repos;

import com.example.auth.models.Animal;
import org.springframework.data.repository.CrudRepository;

// Репозиторий для работы с животными в океанариуме
public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
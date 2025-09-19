package com.example.auth.repos;

import com.example.auth.models.User;
import org.springframework.data.repository.CrudRepository;

// Репозиторий для работы с пользователями в системе океанариума
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
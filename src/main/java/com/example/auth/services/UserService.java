package com.example.auth.services;

import com.example.auth.models.User;
import com.example.auth.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Сервис для работы с пользователями в системе океанариума
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Получить всех пользователей
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Получить пользователя по ID
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Сохранить пользователя
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Удалить пользователя по ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    // Проверить существование пользователя по имени
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    // Найти пользователя по имени
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
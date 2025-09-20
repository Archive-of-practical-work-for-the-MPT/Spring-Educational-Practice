package com.example.project2.service;

import com.example.project2.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User createUser(User user);
    User updateUser(User user);
    User findUserById(Long id);
    void deleteUser(Long id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
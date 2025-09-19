package com.example.project2.service;

import com.example.project2.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    List<User> findAllUsers();
    CompletableFuture<List<User>> findAllUsersAsync();
    User createUser(User user);
    CompletableFuture<User> createUserAsync(User user);
    User updateUser(User user);
    CompletableFuture<User> updateUserAsync(User user);
    User findUserById(Long id);
    CompletableFuture<User> findUserByIdAsync(Long id);
    void deleteUser(Long id);
    CompletableFuture<Void> deleteUserAsync(Long id);
}
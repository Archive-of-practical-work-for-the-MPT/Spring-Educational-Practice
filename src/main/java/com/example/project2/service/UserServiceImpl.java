package com.example.project2.service;

import com.example.project2.exception.UserNotFoundException;
import com.example.project2.model.User;
import com.example.project2.repository.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    @Async
    public CompletableFuture<List<User>> findAllUsersAsync() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    @Async
    public CompletableFuture<User> createUserAsync(User user) {
        return CompletableFuture.completedFuture(repository.save(user));
    }

    @Override
    public User updateUser(User user) {
        // Проверяем, что пользователь существует
        if (!repository.existsById(user.getId())) {
            throw new UserNotFoundException(user.getId());
        }
        return repository.save(user);
    }

    @Override
    @Async
    public CompletableFuture<User> updateUserAsync(User user) {
        // Проверяем, что пользователь существует
        if (!repository.existsById(user.getId())) {
            throw new UserNotFoundException(user.getId());
        }
        return CompletableFuture.completedFuture(repository.save(user));
    }

    @Override
    public User findUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Async
    public CompletableFuture<User> findUserByIdAsync(Long id) {
        return CompletableFuture.completedFuture(
                repository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id))
        );
    }

    @Override
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteUserAsync(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
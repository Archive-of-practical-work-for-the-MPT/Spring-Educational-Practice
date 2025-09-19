package com.example.project2.service;

import com.example.project2.exception.TaskNotFoundException;
import com.example.project2.model.Task;
import com.example.project2.repository.TaskRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    @Override
    @Async
    public CompletableFuture<List<Task>> findAllTasksAsync() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    @Async
    public CompletableFuture<Task> createTaskAsync(Task task) {
        return CompletableFuture.completedFuture(repository.save(task));
    }

    @Override
    public Task updateTask(Task task) {
        // Проверяем, что задача существует
        if (!repository.existsById(task.getId())) {
            throw new TaskNotFoundException(task.getId());
        }
        return repository.save(task);
    }

    @Override
    @Async
    public CompletableFuture<Task> updateTaskAsync(Task task) {
        // Проверяем, что задача существует
        if (!repository.existsById(task.getId())) {
            throw new TaskNotFoundException(task.getId());
        }
        return CompletableFuture.completedFuture(repository.save(task));
    }

    @Override
    public Task findTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    @Async
    public CompletableFuture<Task> findTaskByIdAsync(Long id) {
        return CompletableFuture.completedFuture(
                repository.findById(id)
                        .orElseThrow(() -> new TaskNotFoundException(id))
        );
    }

    @Override
    public void deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteTaskAsync(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
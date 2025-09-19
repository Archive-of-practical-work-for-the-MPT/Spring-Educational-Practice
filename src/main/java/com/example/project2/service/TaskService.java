package com.example.project2.service;

import com.example.project2.model.Task;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TaskService {
    List<Task> findAllTasks();
    CompletableFuture<List<Task>> findAllTasksAsync();
    Task createTask(Task task);
    CompletableFuture<Task> createTaskAsync(Task task);
    Task updateTask(Task task);
    CompletableFuture<Task> updateTaskAsync(Task task);
    Task findTaskById(Long id);
    CompletableFuture<Task> findTaskByIdAsync(Long id);
    void deleteTask(Long id);
    CompletableFuture<Void> deleteTaskAsync(Long id);
}
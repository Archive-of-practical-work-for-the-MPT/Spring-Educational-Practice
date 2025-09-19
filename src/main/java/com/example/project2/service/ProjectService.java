package com.example.project2.service;

import com.example.project2.model.Project;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProjectService {
    List<Project> findAllProjects();
    CompletableFuture<List<Project>> findAllProjectsAsync();
    Project createProject(Project project);
    CompletableFuture<Project> createProjectAsync(Project project);
    Project updateProject(Project project);
    CompletableFuture<Project> updateProjectAsync(Project project);
    Project findProjectById(Long id);
    CompletableFuture<Project> findProjectByIdAsync(Long id);
    void deleteProject(Long id);
    CompletableFuture<Void> deleteProjectAsync(Long id);
}
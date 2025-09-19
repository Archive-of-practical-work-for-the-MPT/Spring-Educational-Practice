package com.example.project2.service;

import com.example.project2.exception.ProjectNotFoundException;
import com.example.project2.model.Project;
import com.example.project2.repository.ProjectRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Project> findAllProjects() {
        return repository.findAll();
    }

    @Override
    @Async
    public CompletableFuture<List<Project>> findAllProjectsAsync() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Override
    public Project createProject(Project project) {
        return repository.save(project);
    }

    @Override
    @Async
    public CompletableFuture<Project> createProjectAsync(Project project) {
        return CompletableFuture.completedFuture(repository.save(project));
    }

    @Override
    public Project updateProject(Project project) {
        // Проверяем, что проект существует
        if (!repository.existsById(project.getId())) {
            throw new ProjectNotFoundException(project.getId());
        }
        return repository.save(project);
    }

    @Override
    @Async
    public CompletableFuture<Project> updateProjectAsync(Project project) {
        // Проверяем, что проект существует
        if (!repository.existsById(project.getId())) {
            throw new ProjectNotFoundException(project.getId());
        }
        return CompletableFuture.completedFuture(repository.save(project));
    }

    @Override
    public Project findProjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    @Async
    public CompletableFuture<Project> findProjectByIdAsync(Long id) {
        return CompletableFuture.completedFuture(
                repository.findById(id)
                        .orElseThrow(() -> new ProjectNotFoundException(id))
        );
    }

    @Override
    public void deleteProject(Long id) {
        if (!repository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteProjectAsync(Long id) {
        if (!repository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }
        repository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
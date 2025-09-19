package com.example.project2.controller;

import com.example.project2.dto.ApiResult;
import com.example.project2.model.Project;
import com.example.project2.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/api/projects")
@Tag(name = "Проекты", description = "API для управления проектами")
public class ProjectApiController {

    private final ProjectService projectService;

    public ProjectApiController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Получить все проекты", description = "Возвращает список всех проектов в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список проектов успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class)))
    })
    @GetMapping
    public CompletableFuture<ApiResult<List<Project>>> getAllProjects() {
        return projectService.findAllProjectsAsync()
                .thenApply(projects -> new ApiResult<>(true, "Проекты успешно получены", projects));
    }

    @Operation(summary = "Получить проект по ID", description = "Возвращает проект по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Проект успешно найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))),
            @ApiResponse(responseCode = "404", description = "Проект не найден")
    })
    @GetMapping("/{id}")
    public CompletableFuture<ApiResult<Project>> getProjectById(
            @Parameter(description = "ID проекта", example = "1")
            @PathVariable Long id) {
        return projectService.findProjectByIdAsync(id)
                .thenApply(project -> new ApiResult<>(true, "Проект успешно найден", project));
    }

    @Operation(summary = "Создать новый проект", description = "Создает новый проект в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Проект успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные проекта")
    })
    @PostMapping
    public CompletableFuture<ApiResult<Project>> createProject(
            @Parameter(description = "Данные нового проекта")
            @RequestBody Project project) {
        return projectService.createProjectAsync(project)
                .thenApply(createdProject -> new ApiResult<>(true, "Проект успешно создан", createdProject));
    }

    @Operation(summary = "Обновить проект", description = "Обновляет данные существующего проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Проект успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные проекта"),
            @ApiResponse(responseCode = "404", description = "Проект не найден")
    })
    @PutMapping("/{id}")
    public CompletableFuture<ApiResult<Project>> updateProject(
            @Parameter(description = "ID проекта", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Обновленные данные проекта")
            @RequestBody Project project) {
        project.setId(id);
        return projectService.updateProjectAsync(project)
                .thenApply(updatedProject -> new ApiResult<>(true, "Проект успешно обновлен", updatedProject));
    }

    @Operation(summary = "Удалить проект", description = "Удаляет проект по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Проект успешно удален"),
            @ApiResponse(responseCode = "404", description = "Проект не найден")
    })
    @DeleteMapping("/{id}")
    public CompletableFuture<ApiResult<Void>> deleteProject(
            @Parameter(description = "ID проекта", example = "1")
            @PathVariable Long id) {
        return projectService.deleteProjectAsync(id)
                .thenApply(v -> new ApiResult<>(true, "Проект успешно удален", null));
    }
}
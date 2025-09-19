package com.example.project2.controller;

import com.example.project2.dto.ApiResult;
import com.example.project2.model.Task;
import com.example.project2.service.TaskService;
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
@RequestMapping("/v1/api/tasks")
@Tag(name = "Задачи", description = "API для управления задачами")
public class TaskApiController {

    private final TaskService taskService;

    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Получить все задачи", description = "Возвращает список всех задач в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)))
    })
    @GetMapping
    public CompletableFuture<ApiResult<List<Task>>> getAllTasks() {
        return taskService.findAllTasksAsync()
                .thenApply(tasks -> new ApiResult<>(true, "Задачи успешно получены", tasks));
    }

    @Operation(summary = "Получить задачу по ID", description = "Возвращает задачу по её уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @GetMapping("/{id}")
    public CompletableFuture<ApiResult<Task>> getTaskById(
            @Parameter(description = "ID задачи", example = "1")
            @PathVariable Long id) {
        return taskService.findTaskByIdAsync(id)
                .thenApply(task -> new ApiResult<>(true, "Задача успешно найдена", task));
    }

    @Operation(summary = "Создать новую задачу", description = "Создает новую задачу в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно создана",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные задачи")
    })
    @PostMapping
    public CompletableFuture<ApiResult<Task>> createTask(
            @Parameter(description = "Данные новой задачи")
            @RequestBody Task task) {
        return taskService.createTaskAsync(task)
                .thenApply(createdTask -> new ApiResult<>(true, "Задача успешно создана", createdTask));
    }

    @Operation(summary = "Обновить задачу", description = "Обновляет данные существующей задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно обновлена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные задачи"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @PutMapping("/{id}")
    public CompletableFuture<ApiResult<Task>> updateTask(
            @Parameter(description = "ID задачи", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Обновленные данные задачи")
            @RequestBody Task task) {
        task.setId(id);
        return taskService.updateTaskAsync(task)
                .thenApply(updatedTask -> new ApiResult<>(true, "Задача успешно обновлена", updatedTask));
    }

    @Operation(summary = "Удалить задачу", description = "Удаляет задачу по её уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @DeleteMapping("/{id}")
    public CompletableFuture<ApiResult<Void>> deleteTask(
            @Parameter(description = "ID задачи", example = "1")
            @PathVariable Long id) {
        return taskService.deleteTaskAsync(id)
                .thenApply(v -> new ApiResult<>(true, "Задача успешно удалена", null));
    }
}
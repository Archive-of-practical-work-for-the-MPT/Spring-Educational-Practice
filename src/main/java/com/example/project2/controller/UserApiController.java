package com.example.project2.controller;

import com.example.project2.dto.ApiResult;
import com.example.project2.model.User;
import com.example.project2.service.UserService;
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
@RequestMapping("/v1/api/users")
@Tag(name = "Пользователи", description = "API для управления пользователями")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @GetMapping
    public CompletableFuture<ApiResult<List<User>>> getAllUsers() {
        return userService.findAllUsersAsync()
                .thenApply(users -> new ApiResult<>(true, "Пользователи успешно получены", users));
    }

    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public CompletableFuture<ApiResult<User>> getUserById(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Long id) {
        return userService.findUserByIdAsync(id)
                .thenApply(user -> new ApiResult<>(true, "Пользователь успешно найден", user));
    }

    @Operation(summary = "Создать нового пользователя", description = "Создает нового пользователя в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные пользователя")
    })
    @PostMapping
    public CompletableFuture<ApiResult<User>> createUser(
            @Parameter(description = "Данные нового пользователя")
            @RequestBody User user) {
        return userService.createUserAsync(user)
                .thenApply(createdUser -> new ApiResult<>(true, "Пользователь успешно создан", createdUser));
    }

    @Operation(summary = "Обновить пользователя", description = "Обновляет данные существующего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные пользователя"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/{id}")
    public CompletableFuture<ApiResult<User>> updateUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Обновленные данные пользователя")
            @RequestBody User user) {
        user.setId(id);
        return userService.updateUserAsync(user)
                .thenApply(updatedUser -> new ApiResult<>(true, "Пользователь успешно обновлен", updatedUser));
    }

    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    public CompletableFuture<ApiResult<Void>> deleteUser(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Long id) {
        return userService.deleteUserAsync(id)
                .thenApply(v -> new ApiResult<>(true, "Пользователь успешно удален", null));
    }
}
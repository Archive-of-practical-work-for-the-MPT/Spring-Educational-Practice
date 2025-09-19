package com.example.project2.exception;

import com.example.project2.dto.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработчик для UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResult<Void>> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ApiResult<Void> response = new ApiResult<>(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Обработчик для ProjectNotFoundException
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ApiResult<Void>> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request) {
        ApiResult<Void> response = new ApiResult<>(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Обработчик для TaskNotFoundException
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResult<Void>> handleTaskNotFoundException(TaskNotFoundException ex, WebRequest request) {
        ApiResult<Void> response = new ApiResult<>(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Обработчик для ValidationException
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResult<Void>> handleValidationException(ValidationException ex, WebRequest request) {
        ApiResult<Void> response = new ApiResult<>(false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Обработчик для общих исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> handleGenericException(Exception ex, WebRequest request) {
        ApiResult<Void> response = new ApiResult<>(false, "Произошла внутренняя ошибка сервера");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
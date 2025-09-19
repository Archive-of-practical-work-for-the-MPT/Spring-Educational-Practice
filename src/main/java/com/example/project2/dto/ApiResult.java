package com.example.project2.dto;

import java.time.LocalDateTime;

public class ApiResult<T> {
    private LocalDateTime timestamp;
    private boolean success;
    private String message;
    private T data;

    public ApiResult() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResult(boolean success, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResult(boolean success, String message) {
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = message;
    }

    // Геттеры и сеттеры
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
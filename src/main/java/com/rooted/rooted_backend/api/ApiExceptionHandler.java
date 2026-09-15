package com.rooted.rooted_backend.api;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rooted.rooted_backend.subscription.DuplicateSubscriptionException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> fields = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> fields.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ApiError("Validation failed.", fields));
    }

    @ExceptionHandler(DuplicateSubscriptionException.class)
    public ResponseEntity<ApiError> handleDuplicate(DuplicateSubscriptionException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(exception.getMessage(), Map.of("email", exception.getMessage())));
    }

    public record ApiError(String message, Map<String, String> fields, Instant timestamp) {
        public ApiError(String message, Map<String, String> fields) {
            this(message, fields, Instant.now());
        }
    }
}

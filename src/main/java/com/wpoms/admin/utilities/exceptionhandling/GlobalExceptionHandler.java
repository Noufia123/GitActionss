package com.wpoms.admin.utilities.exceptionhandling;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation Errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("error", "Validation Failed");
        response.put("errors", errors);

        return response;
    }

    // Customer Not Found
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleCustomerNotFound(CustomerNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();

        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 404);
        response.put("error", "Customer Not Found");
        response.put("message", ex.getMessage());

        return response;
    }

    // Duplicate Data (Email / Phone)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleDuplicateData(IllegalArgumentException ex) {

        Map<String, Object> response = new HashMap<>();

        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("error", "Duplicate Data");
        response.put("message", ex.getMessage());

        return response;
    }

    // Runtime Errors
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleRuntimeException(RuntimeException ex) {

        Map<String, Object> response = new HashMap<>();

        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("error", "Runtime Error");
        response.put("message", ex.getMessage());

        return response;
    }

    // Generic Exception (Fallback)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGenericException(Exception ex) {

        Map<String, Object> response = new HashMap<>();

        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 500);
        response.put("error", "Internal Server Error");
        response.put("message", "Something went wrong. Please try again.");

        return response;
    }

}

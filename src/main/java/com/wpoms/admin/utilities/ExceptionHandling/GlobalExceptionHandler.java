package com.wpoms.admin.utilities.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Validation Errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> response = new HashMap<>();
        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("errors", errors);

        return response;
    }

    

    //  2. Business Errors (Runtime)
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleRuntimeException(RuntimeException ex) {

        Map<String, Object> response = new HashMap<>();
        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("message", ex.getMessage());

        return response;
    }

    //  3. Generic Errors (Fallback)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGenericException(Exception ex) {

        Map<String, Object> response = new HashMap<>();
        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 500);
        response.put("message", "Something went wrong. Please try again.");

        return response;
    }

    // 4. Business Error(Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBusinessException(IllegalArgumentException ex){
        Map<String,Object> response = new HashMap<>();
        // response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("message", ex.getMessage());

        return response;
    }


}

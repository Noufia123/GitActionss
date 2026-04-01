// package com.wpoms.admin.utilities.exceptions;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.NoSuchElementException;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     //  Validation errors (@Valid request body)
//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

//         Map<String, String> errors = new HashMap<>();

//         for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//             errors.putIfAbsent(error.getField(), error.getDefaultMessage());
//         }

//         return new ResponseEntity<>(
//                 Map.of(
//                         "status", "FAILED",
//                         "message", "Validation failed",
//                         "errors", errors
//                 ),
//                 HttpStatus.BAD_REQUEST
//         );
//     }

//     //  Bad Request (manual validation like GST length)
//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<?> handleBadRequest(IllegalArgumentException ex) {

//         return new ResponseEntity<>(
//                 Map.of(
//                         "status", "FAILED",
//                         "message", ex.getMessage()
//                 ),
//                 HttpStatus.BAD_REQUEST
//         );
//     }

//     //  Conflict (duplicate email, phone, GST)
//     @ExceptionHandler(IllegalStateException.class)
//     public ResponseEntity<?> handleConflict(IllegalStateException ex) {

//         return new ResponseEntity<>(
//                 Map.of(
//                         "status", "FAILED",
//                         "message", ex.getMessage()
//                 ),
//                 HttpStatus.CONFLICT
//         );
//     }

//     // Not Found
//     @ExceptionHandler(NoSuchElementException.class)
//     public ResponseEntity<?> handleNotFound(NoSuchElementException ex) {

//         return new ResponseEntity<>(
//                 Map.of(
//                         "status", "FAILED",
//                         "message", ex.getMessage()
//                 ),
//                 HttpStatus.NOT_FOUND
//         );
//     }

//     // Handle query param / path variable validation
//     @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
//     public ResponseEntity<?> handleConstraintViolation(
//             jakarta.validation.ConstraintViolationException ex) {

//         Map<String, String> errors = new HashMap<>();

//         ex.getConstraintViolations().forEach(v ->
//                 errors.put(v.getPropertyPath().toString(), v.getMessage())
//         );

//         return new ResponseEntity<>(
//                 Map.of(
//                         "status", "FAILED",
//                         "message", "Validation failed",
//                         "errors", errors
//                 ),
//                 HttpStatus.BAD_REQUEST
//         );
//     }

//     // Catch-all (VERY IMPORTANT)
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<?> handleAll(Exception ex) {

//         return new ResponseEntity<>(
//                 Map.of(
//                         "status", "FAILED",
//                         "message", "Something went wrong"
//                 ),
//                 HttpStatus.INTERNAL_SERVER_ERROR
//         );
//     }

// }

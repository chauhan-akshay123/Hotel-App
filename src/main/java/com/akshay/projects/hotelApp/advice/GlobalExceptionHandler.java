package com.akshay.projects.hotelApp.advice;

import com.akshay.projects.hotelApp.entity.enums.ErrorStatus;
import com.akshay.projects.hotelApp.exception.*;
import com.akshay.projects.hotelApp.exception.IllegalStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(
            ResourceNotFoundException ex
    ) {

       ApiError error = ApiError.builder()
               .status(String.valueOf(ErrorStatus.NOT_FOUND))
               .message(ex.getMessage())
               .build();

       ApiResponse<Void> response = ApiResponse.<Void>builder()
               .timestamp(LocalDateTime.now())
               .data(null)
               .error(error)
               .build();

       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(response);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateResource(
            DuplicateResourceException ex
    ) {

       ApiError error = ApiError.builder()
               .status(String.valueOf(ErrorStatus.CONFLICT))
               .message(ex.getMessage())
               .build();

       ApiResponse<Void> response = ApiResponse.<Void>builder()
               .timestamp(LocalDateTime.now())
               .data(null)
               .error(error)
               .build();

       return ResponseEntity
               .status(HttpStatus.CONFLICT)
               .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {

       List<String> subErrors = ex.getBindingResult()
               .getFieldErrors()
               .stream()
               .map(fieldError ->
                       fieldError.getField()
               +": "
               + fieldError.getDefaultMessage()
               )
               .toList();

       ApiError error = ApiError.builder()
               .status(String.valueOf(ErrorStatus.BAD_REQUEST))
               .message("Validation failed")
               .subErrors(subErrors)
               .build();

       ApiResponse<Void> response = ApiResponse.<Void>builder()
               .timestamp(LocalDateTime.now())
               .data(null)
               .error(error)
               .build();

       return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception ex
    ) {

        ApiError error = ApiError.builder()
                .status(String.valueOf(ErrorStatus.INTERNAL_SERVER_ERROR))
                .message("An unexpected error occurred")
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now())
                .data(null)
                .error(error)
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler({
            HotelAlreadyActiveException.class,
            HotelAlreadyInactiveException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleHotelStateException(
            RuntimeException ex
    ) {

        ApiError error = ApiError.builder()
                .status(String.valueOf(ErrorStatus.CONFLICT))
                .message(ex.getMessage())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now())
                .data(null)
                .error(error)
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}

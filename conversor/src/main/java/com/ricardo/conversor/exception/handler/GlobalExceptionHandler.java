package com.ricardo.conversor.exception.handler;

import com.ricardo.conversor.exception.ConversionRateNotFoundException;
import com.ricardo.conversor.exception.NegativeAmountNotAllowedException;
import com.ricardo.conversor.exception.UnsupportedUnitException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UnsupportedUnitException.class,
            NegativeAmountNotAllowedException.class,
            ConversionRateNotFoundException.class
    })
    public ResponseEntity<Map<String, String>> handleBusinessExceptions(RuntimeException ex, WebRequest request) {
        Map<String, String> errorResponse = Map.of(
                "status", "400 BAD_REQUEST",
                "error", ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        String errors = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));

        Map<String, Object> errorResponse = Map.of(
                "status", "400 BAD_REQUEST",
                "error", "Erro de validação",
                "details", errors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex, WebRequest request) {
        Map<String, String> errorResponse = Map.of(
                "status", "500 INTERNAL_SERVER_ERROR",
                "error", "Ocorreu um erro inesperado."
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package com.ricardo.imc.exception.handler; 

import com.ricardo.imc.exception.DivisionByZeroLikeException;
import com.ricardo.imc.exception.InvalidHeightException;
import com.ricardo.imc.exception.InvalidWeightException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler({
            InvalidHeightException.class,
            InvalidWeightException.class,
            DivisionByZeroLikeException.class
    })
    public ResponseEntity<Map<String, String>> handleImcValidationExceptions(RuntimeException ex, WebRequest request) {
        Map<String, String> errorResponse = Map.of(
                "status", "400 BAD_REQUEST",
                "erro", ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingParams(MissingServletRequestParameterException ex, WebRequest request) {
        String erroMsg = "Parâmetro obrigatório ausente: " + ex.getParameterName();
        Map<String, String> errorResponse = Map.of(
                "status", "400 BAD_REQUEST",
                "erro", erroMsg
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
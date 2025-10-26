package com.ricardo.imc.exception;

public class DivisionByZeroLikeException extends RuntimeException {
    public DivisionByZeroLikeException(String message) {
        super(message);
    }
}
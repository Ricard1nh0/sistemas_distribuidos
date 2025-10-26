package com.ricardo.imc.exception;

public class InvalidWeightException extends RuntimeException {
    public InvalidWeightException(String message) {
        super(message);
    }
}
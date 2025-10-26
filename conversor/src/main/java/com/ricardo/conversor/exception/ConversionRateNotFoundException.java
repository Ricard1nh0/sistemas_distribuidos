package com.ricardo.conversor.exception;

public class ConversionRateNotFoundException extends RuntimeException {
    public ConversionRateNotFoundException(String message) {
        super(message);
    }
}

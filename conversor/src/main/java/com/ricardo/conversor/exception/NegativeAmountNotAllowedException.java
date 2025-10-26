package com.ricardo.conversor.exception;

public class NegativeAmountNotAllowedException extends RuntimeException {
    public NegativeAmountNotAllowedException(String message) {
        super(message);
    }
}

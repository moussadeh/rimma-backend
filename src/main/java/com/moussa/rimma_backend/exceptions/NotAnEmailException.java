package com.moussa.rimma_backend.exceptions;

public class NotAnEmailException extends RuntimeException {

    public NotAnEmailException(String message) {
        super(message);
    }
}

package com.moussa.rimma_backend.exceptions;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("email ou mot de passe incorrect");
    }
}

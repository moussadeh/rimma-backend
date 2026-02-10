package com.moussa.rimma_backend.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String email) {
        super("L'email '" + email + "' est déjà utilisé");
    }
}

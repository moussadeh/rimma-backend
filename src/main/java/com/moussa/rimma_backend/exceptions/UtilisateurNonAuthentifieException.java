package com.moussa.rimma_backend.exceptions;

public class UtilisateurNonAuthentifieException extends RuntimeException {

    public UtilisateurNonAuthentifieException(String message) {
        super(message);
    }
}

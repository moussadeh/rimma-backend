package com.moussa.rimma_backend.exceptions;

public class NotOwnAnnonceException extends RuntimeException {

    public NotOwnAnnonceException(String message) {
        super(message);
    }
}

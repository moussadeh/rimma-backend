package com.moussa.rimma_backend.exceptions;

public class AlereadyHasThisRoleException extends RuntimeException {

    public AlereadyHasThisRoleException(String message) {
        super(message);
    }
}

package com.moussa.rimma_backend.exceptions;

public class AlereadyInReservationsException extends RuntimeException {

    public AlereadyInReservationsException(String message) {
        super(message);
    }
}

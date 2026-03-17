package com.moussa.rimma_backend.exceptions;

public class NotFoundReservationException extends RuntimeException {

    public NotFoundReservationException(String message) {
        super(message);
    }
}

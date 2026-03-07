package com.moussa.rimma_backend.exceptions;

public class AnnonceNotFoundException extends RuntimeException {

    public AnnonceNotFoundException() {
        super("Annonce introuvable");
    }
}

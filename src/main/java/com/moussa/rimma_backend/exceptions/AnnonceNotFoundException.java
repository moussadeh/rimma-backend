package com.moussa.rimma_backend.exceptions;

public class AnnonceNotFoundException extends RuntimeException {

    public AnnonceNotFoundException(Long id) {
        super("Annonce avec l'id " + id + " introuvable");
    }
}

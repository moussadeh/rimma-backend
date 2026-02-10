package com.moussa.rimma_backend.exceptions;

public class UtilisateurNotFoundException extends RuntimeException {

    public UtilisateurNotFoundException(Long id) {
        super("Utilisateur avec l'id " + id + " introuvable");
    }
}

package com.moussa.rimma_backend.exceptions;

public class UtilisateurNotFoundException extends RuntimeException {

    public UtilisateurNotFoundException() {
        super("Utilisateur introuvable");
    }
}

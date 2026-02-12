package com.moussa.rimma_backend.exceptions;

public class UtilisateurWithEmailNotFoundException extends RuntimeException {

    public UtilisateurWithEmailNotFoundException(String email) {
        super("L'utilisateur avec l'email " + email + " est introuvable");
    }
}

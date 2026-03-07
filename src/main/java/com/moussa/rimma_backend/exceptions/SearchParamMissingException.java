package com.moussa.rimma_backend.exceptions;

public class SearchParamMissingException extends RuntimeException {

    public SearchParamMissingException() {
        super("Au moins un critère de recherche doit être renseigné (titre, ville ou quartier)");
    }
}

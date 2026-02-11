package com.moussa.rimma_backend.exceptions;

public class ImageLimitException extends RuntimeException {

    public ImageLimitException() {
        super("Une annonce doit contenir entre 1 et 10 images");
    }
}

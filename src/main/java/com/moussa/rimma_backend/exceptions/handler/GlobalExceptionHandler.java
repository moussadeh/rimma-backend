package com.moussa.rimma_backend.exceptions.handler;

import com.moussa.rimma_backend.exceptions.*;
import com.moussa.rimma_backend.models.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(UtilisateurNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUtilisateurNotFound(UtilisateurNotFoundException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(AnnonceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAnnonceNotFound(AnnonceNotFoundException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyUsed(EmailAlreadyUsedException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(SearchParamMissingException.class)
    public ResponseEntity<ErrorResponse> handleSearchParamMissing(SearchParamMissingException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(AnnonceNotValidException.class)
    public ResponseEntity<ErrorResponse> handleAnnonceNotValid(AnnonceNotValidException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(ImageLimitException.class)
    public ResponseEntity<ErrorResponse> handleImageLimit(ImageLimitException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), request);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectPassword(IncorrectPasswordException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    @ExceptionHandler(UtilisateurWithEmailNotFoundException.class)
    public  ResponseEntity<ErrorResponse> handleUtilisateurWithEmailNotFound(UtilisateurWithEmailNotFoundException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(UtilisateurNonAuthentifieException.class)
    public ResponseEntity<ErrorResponse> handleUtilisateurNonAuthentifieException(UtilisateurNonAuthentifieException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    @ExceptionHandler(NotAnEmailException.class)
    public ResponseEntity<ErrorResponse> handleNotAnEmailException(NotAnEmailException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    // handler est déclenché quand Spring Security bloque une action par exemple @PreAuthorize
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, "Accès refusé", request);
    }

    // Exception générale
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        // return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur interne est survenue", request);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }

}

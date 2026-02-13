package com.moussa.rimma_backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String motDePasse;
}

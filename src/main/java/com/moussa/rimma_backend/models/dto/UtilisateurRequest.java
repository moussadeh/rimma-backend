package com.moussa.rimma_backend.models.dto;

import com.moussa.rimma_backend.models.enums.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UtilisateurRequest {

    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String motDePasse;
    private RoleType role;
}

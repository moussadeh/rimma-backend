package com.moussa.rimma_backend.models.dto;

import com.moussa.rimma_backend.models.enums.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurResponse {

    private String nom;
    private String prenom;
    private String telephone;
    private Boolean active;
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;
}

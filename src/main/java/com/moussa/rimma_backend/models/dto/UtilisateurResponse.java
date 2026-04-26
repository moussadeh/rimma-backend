package com.moussa.rimma_backend.models.dto;

import com.moussa.rimma_backend.models.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurResponse {

    private String nom;
    private String prenom;
    private String telephone;
    private Boolean active;
    private String email;
    private Set<RoleType> roles;
}

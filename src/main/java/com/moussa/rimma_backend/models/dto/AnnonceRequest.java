package com.moussa.rimma_backend.models.dto;

import com.moussa.rimma_backend.models.enums.HebergementType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnnonceRequest {

    private String titre;
    private String description;
    private Double prix;
    private String ville;
    private String quartier;

    @Enumerated(EnumType.STRING)
    private HebergementType hebergement;
}

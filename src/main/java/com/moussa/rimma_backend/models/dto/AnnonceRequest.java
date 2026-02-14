package com.moussa.rimma_backend.models.dto;

import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnnonceRequest {

    private String titre;
    private String description;
    private Double prix;
    private String ville;
    private String quartier;
    private HebergementType hebergement;
    private StatutType statut;
    public List<String> images;
}

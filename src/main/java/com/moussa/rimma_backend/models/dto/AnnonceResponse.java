package com.moussa.rimma_backend.models.dto;

import com.moussa.rimma_backend.models.Image;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class AnnonceResponse {

    private String titre;
    private String description;
    private Double prix;
    private String ville;
    private String quartier;
    private Boolean actif;
    private Boolean valide;

    @Enumerated(EnumType.STRING)
    private HebergementType hebergement;

    @Enumerated(EnumType.STRING)
    private StatutType statut;

    private String utilisateur;

    private List<String> images;
}

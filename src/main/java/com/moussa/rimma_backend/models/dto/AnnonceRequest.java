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

    // options
    private Double surface;
    private Integer nombreChambres = null;
    private Integer nombreSallesDeBain = null;
    private Integer nombreEtages = null;
    private Boolean isJardin = false;
    private Boolean isPiscine = false;
    private Boolean isAscenseur = false;
    private Boolean isConstructible = false;
    private Boolean isMeublee = false;
    private Boolean isClimatisee = false;
    private Boolean isWifi = false;
    private Boolean isCloture = false;
    private Boolean isAccesRoute = false;
    private Boolean isElectricite = false;
    private Boolean isEau = false;

    public List<String> images;
}

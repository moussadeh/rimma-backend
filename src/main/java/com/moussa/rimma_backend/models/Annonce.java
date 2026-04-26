package com.moussa.rimma_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "annonces")
@AllArgsConstructor
@NoArgsConstructor
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private Double prix;
    private String ville;
    private String quartier;
    private Boolean actif = Boolean.TRUE;
    private Boolean valide = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private HebergementType hebergement;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatutType statut;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnore
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "annonce", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @ManyToMany(mappedBy = "favoris")
    @JsonIgnore
    private List<Utilisateur> utilisateursFavoris = new ArrayList<>();

    @OneToMany(mappedBy = "annonce", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    // options
    @Column(nullable = true)
    private Double surface;
    @Column(nullable = true)
    private Integer nombreChambres = null;
    @Column(nullable = true)
    private Integer nombreSallesDeBain = null;
    @Column(nullable = true)
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

}

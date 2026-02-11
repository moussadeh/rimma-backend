package com.moussa.rimma_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

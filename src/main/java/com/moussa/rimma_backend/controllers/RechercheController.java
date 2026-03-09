package com.moussa.rimma_backend.controllers;


import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.services.AnnonceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Recherche", description = "Permet la recherche et les filtres")
@RestController
@RequestMapping("/rimma/api/annonces")
public class RechercheController {

    private final AnnonceService annonceService;

    public RechercheController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping("/search")
    @Operation(summary = "Recherche d'annonces => Permet la recherche d'annonces à partir d'une chaine.")
    public ResponseEntity<List<Annonce>> searchAnnonces(@RequestParam String query) {
        return ResponseEntity.ok(annonceService.searchAnnonces(query));
    }

    @GetMapping("/me/search")
    @PreAuthorize("hasRole('CLIENT')")
    @Operation(summary = "Recherche d'annonces => Permet à un client de rechercher sur ses propres annonces.")
    public ResponseEntity<List<Annonce>> searchMyOwnAnnonces(@RequestParam String query, Authentication authentication) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return ResponseEntity.ok(annonceService.searchMyOwnAnnonces(utilisateur.getId(), query));
    }
}

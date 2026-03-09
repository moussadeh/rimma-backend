package com.moussa.rimma_backend.controllers;


import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.services.AnnonceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Recherche", description = "Permet la recherche et les filtres")
@RestController
@RequestMapping("/rimma/api")
public class RechercheController {

    private final AnnonceService annonceService;

    public RechercheController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping("/search")
    @Operation(summary = "Recherche d'annonces => Permet la recherche d'annonces par titre, ville et quartier.")
    public ResponseEntity<List<Annonce>> searchAnnonces(@RequestParam String query) {
        return ResponseEntity.ok(annonceService.searchAnnonces(query));
    }
}

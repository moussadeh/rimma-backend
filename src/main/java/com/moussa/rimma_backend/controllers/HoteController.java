package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;
import com.moussa.rimma_backend.services.AnnonceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hôte", description = "Gestion des actions effectuées par un hôte (proprietaire des annonce)")
@RestController
@RequestMapping("/rimma/api/hote")
public class HoteController {

    private final AnnonceService annonceService;

    public HoteController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @Operation(summary = "Création d'une annonce => Permet à un hôte de créer une nouvelle annonce.")
    @PostMapping("/creer")
    @PreAuthorize("hasRole('HOTE')")
    public ResponseEntity<Annonce> creerAnnonce(Authentication authentication, @Valid @RequestBody AnnonceRequest annonceRequest){
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        Annonce annonce = annonceService.creerAnnonce(utilisateur.getId(), annonceRequest);
        return ResponseEntity.ok(annonce);
    }

    @GetMapping("/annonces/me")
    @PreAuthorize("hasRole('HOTE')")
    @Operation(summary = "Recupère toutes les annonces de l'hôte connecté")
    public ResponseEntity<List<Annonce>> getMesAnnonces(Authentication authentication) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return ResponseEntity.ok(annonceService.getAnnoncesByUtilisateur(utilisateur.getId()));
    }
}

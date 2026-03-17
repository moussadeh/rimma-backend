package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.services.AnnonceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CLIENT", description = "Gestion des actions que peut effectuer un client (personne qui reserve des annonces)")
@RestController
@RequestMapping("/rimma/api/client")
public class ClientController {

    private final AnnonceService annonceService;

    public ClientController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @Operation(summary = "Permet à un client d'ajouter des annonces à ses favoris.")
    @PostMapping("/me/add/favori/{annonceId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> ajouterFavori(Authentication authentication, @PathVariable Long annonceId) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        annonceService.ajouterFavori(utilisateur.getId(), annonceId);
        return ResponseEntity.ok("L'annonce a bien été ajouté à vos favoris");
    }

    @Operation(summary = "Permet à un client de retirer des annonces de ses favoris.")
    @DeleteMapping("/me/remove/favori/{annonceId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> retirerFavori(Authentication authentication, @PathVariable Long annonceId) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        annonceService.retirerFavori(utilisateur.getId(), annonceId);
        return ResponseEntity.ok("L'annonce a bien été retiré de vos favoris");
    }

    @Operation(summary = "Permet à un client de recupérer ses annonces favorites.")
    @GetMapping("/me/favoris")
    @PreAuthorize("hasRole('CLIENT')")
    public List<Annonce> recupererFavoris(Authentication authentication) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return annonceService.getFavoris(utilisateur.getId());
    }
}

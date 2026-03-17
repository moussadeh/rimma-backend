package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.UtilisateurRequest;
import com.moussa.rimma_backend.models.dto.UtilisateurResponse;
import com.moussa.rimma_backend.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs de l'application rimma")
@RestController
@RequestMapping("/rimma/api/utilisateurs")
@AllArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Operation(summary = "Récupération de tous les utilisateurs => Retourne la liste complète des utilisateurs.")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UtilisateurResponse>> getAllUtilisateurs(Pageable  pageable) {
        return ResponseEntity.ok(utilisateurService.trouverTous(pageable));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Utilisateur connecté => Retourne les informations de l'utilisateur authentifié.")
    public ResponseEntity<UtilisateurResponse> getCurrentUser(Authentication authentication) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return ResponseEntity.ok(utilisateurService.utilisateurConnectee(utilisateur));
    }

    @Operation(summary = "Récupération d'un utilisateur par ID => Permet à un ADMIN de consulter les informations d’un utilisateur.")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.trouverParId(id));
    }

    @Operation(summary = "Création d'un utilisateur => Permet à un ADMIN de créer un nouvel utilisateur.")
    @PostMapping("/creer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur>  creerUtilisateur(@Valid @RequestBody UtilisateurRequest utilisateurRequest) {
        return ResponseEntity.ok(utilisateurService.creerUtilisateur(utilisateurRequest));
    }

    @Operation(summary = "Modification d'un utilisateur => Permet à un utilisateur de modifier ces propres informations.")
    @PutMapping("/me")
    @PreAuthorize("hasRole('CLIENT') or hasRole('HOTE')")
    public ResponseEntity<Utilisateur> modifierUtilisateurByUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.modifierUtilisateur(id, utilisateur));
    }

    @Operation(summary = "Modification d'un utilisateur => Permet à un ADMIN de modifier les informations d’un utilisateur.")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.modifierUtilisateur(id, utilisateur));
    }

    @Operation(summary = "Suppression d'un utilisateur => Permet uniquement à un ADMIN de supprimer définitivement un utilisateur.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}

package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;
import com.moussa.rimma_backend.services.AnnonceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Annonces", description = "Gestion des annonces des annonces publiées")
@RestController
@RequestMapping("/rimma/api/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @Operation(
            summary = "Récupération de toutes les annonces valides",
            description = "Retourne la liste de toutes les annonces actives (valid = true). Cette route est publique."
    )
    @GetMapping
    public List<Annonce> getAnnonces() {
        return annonceService.getAnnonces();
    }

    @Operation(
            summary = "Récupération des annonces d’un utilisateur",
            description = "Retourne toutes les annonces associées à un utilisateur spécifique."
    )
    @GetMapping("/utilisateur/{id}")
    public List<Annonce> findAnnoncesByUtilisateur(@PathVariable Long id){
        return annonceService.getAnnoncesByUtilisateur(id);
    }

    @Operation(
            summary = "Création d'une annonce",
            description = "Permet à un utilisateur (CLIENT propriétaire ou ADMIN) de créer une nouvelle annonce."
    )
    @PostMapping("/utilisateur/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENT') and #id == principal.id)")
    public ResponseEntity<Annonce> creerAnnonce(@PathVariable Long id, @Valid @RequestBody AnnonceRequest annonceRequest){
        Annonce annonce = annonceService.creerAnnonce(id, annonceRequest);
        return ResponseEntity.ok(annonce);
    }

    @Operation(
            summary = "Modification d'une annonce",
            description = "Permet au propriétaire de l’annonce ou à un ADMIN de modifier une annonce existante."
    )
    @PutMapping("/annonce/{id}")
    @PreAuthorize("hasRole('ADMIN') or @annonceService.isOwner(#id, principal.id)")
    public Annonce modifierAnnonce(@PathVariable Long id, @Valid @RequestBody AnnonceRequest annonceRequest){
        return annonceService.modifierAnnonce(id, annonceRequest);
    }

    @Operation(
            summary = "Désactivation d'une annonce",
            description = "Permet au propriétaire ou à un ADMIN de désactiver une annonce (elle ne sera plus visible publiquement)."
    )
    @PatchMapping("/annonce/{id}/desactiver")
    @PreAuthorize("hasRole('ADMIN') or @annonceService.isOwner(#id, principal.id)")
    public Annonce desactiverAnnonce(@PathVariable Long id){
        return annonceService.desactiverAnnonce(id);
    }

    @Operation(
            summary = "Activation d'une annonce",
            description = "Permet au propriétaire ou à un ADMIN de réactiver une annonce."
    )
    @PatchMapping("/annonce/{id}/activer")
    @PreAuthorize("hasRole('ADMIN') or @annonceService.isOwner(#id, principal.id)")
    public Annonce activerAnnonce(@PathVariable Long id){
        return annonceService.activerAnnonce(id);
    }

    @Operation(
            summary = "Invalidation d'une annonce",
            description = "Permet à un ADMIN d'invalider une annonce créé."
    )
    @PatchMapping("/annonce/{id}/invalider")
    @PreAuthorize("hasRole('ADMIN')")
    public Annonce invaliderAnnonce(@PathVariable Long id){
        return annonceService.invaliderAnnonce(id);
    }

    @Operation(
            summary = "Validation d'une annonce",
            description = "Permet à un ADMIN de valider une annonce créé."
    )
    @PatchMapping("/annonce/{id}/valider")
    @PreAuthorize("hasRole('ADMIN')")
    public Annonce validerAnnonce(@PathVariable Long id){
        return annonceService.validerAnnonce(id);
    }
}

package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.exceptions.UtilisateurNonAuthentifieException;
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

@Tag(name = "Annonces", description = "Gestion des annonces des annonces publiées")
@RestController
@RequestMapping("/rimma/api/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @Operation(summary = "Création d'une annonce => Permet à un utilisateur de créer une nouvelle annonce.")
    @PostMapping("/creer")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT')")
    public ResponseEntity<Annonce> creerAnnonce(Authentication authentication, @Valid @RequestBody AnnonceRequest annonceRequest){
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        Annonce annonce = annonceService.creerAnnonce(utilisateur.getId(), annonceRequest);
        return ResponseEntity.ok(annonce);
    }

    @Operation(summary = "Récupération de toutes les annonces valides => Retourne la liste de toutes les annonces validées. Cette route est publique.")
    @GetMapping
    public List<Annonce> getAnnonces() {
        return annonceService.getAnnonces();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CLIENT')")
    @Operation(summary = "Recupère toutes les annonces de l'utilisateur connecté")
    public ResponseEntity<List<Annonce>> getMesAnnonces(Authentication authentication) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return ResponseEntity.ok(annonceService.getAnnoncesByUtilisateur(utilisateur.getId()));
    }

    @Operation(summary = "Récupération des annonces d’un utilisateur => Permet à un ADMIN toutes les annonces associées à un utilisateur spécifique.")
    @GetMapping("/utilisateur/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Annonce> findAnnoncesByUtilisateur(@PathVariable Long id){
        return annonceService.getAnnoncesByUtilisateur(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Recherche d'annonces => Permet la recherche d'annonces par titre, ville et quartier.")
    public ResponseEntity<List<Annonce>> searchAnnonces(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) String quartier
    ) {
        return ResponseEntity.ok(annonceService.searchAnnonces(titre, ville, quartier));
    }

    @Operation(summary = "Modification d'une annonce => Permet au propriétaire de l’annonce ou à un ADMIN de modifier une annonce existante.")
    @PutMapping("/annonce/{id}")
    @PreAuthorize("hasRole('ADMIN') or @annonceService.isOwner(#id, principal.id)")
    public Annonce modifierAnnonce(@PathVariable Long id, @Valid @RequestBody AnnonceRequest annonceRequest){
        return annonceService.modifierAnnonce(id, annonceRequest);
    }

    @Operation(summary = "Désactivation d'une annonce => Permet au propriétaire ou à un ADMIN de désactiver une annonce (elle ne sera plus visible publiquement)")
    @PatchMapping("/annonce/{id}/desactiver")
    @PreAuthorize("hasRole('ADMIN') or @annonceService.isOwner(#id, principal.id)")
    public Annonce desactiverAnnonce(@PathVariable Long id){
        return annonceService.desactiverAnnonce(id);
    }

    @Operation(summary = "Activation d'une annonce => Permet au propriétaire ou à un ADMIN de réactiver une annonce.")
    @PatchMapping("/annonce/{id}/activer")
    @PreAuthorize("hasRole('ADMIN') or @annonceService.isOwner(#id, principal.id)")
    public Annonce activerAnnonce(@PathVariable Long id){
        return annonceService.activerAnnonce(id);
    }

    @Operation(summary = "Invalidation d'une annonce => Permet à un ADMIN d'invalider une annonce créé.")
    @PatchMapping("/annonce/{id}/invalider")
    @PreAuthorize("hasRole('ADMIN')")
    public Annonce invaliderAnnonce(@PathVariable Long id){
        return annonceService.invaliderAnnonce(id);
    }

    @Operation(summary = "Validation d'une annonce => Permet à un ADMIN de valider une annonce créé.")
    @PatchMapping("/annonce/{id}/valider")
    @PreAuthorize("hasRole('ADMIN')")
    public Annonce validerAnnonce(@PathVariable Long id){
        return annonceService.validerAnnonce(id);
    }

    @GetMapping("/annonce/{id}")
    @Operation(summary = "Récupération d'une annonce => Retourne le détail d'une annonce spécifique.")
    public ResponseEntity<Annonce> getAnnonce(@PathVariable Long id){
        return ResponseEntity.ok(annonceService.getAnnonceById(id));
    }
}

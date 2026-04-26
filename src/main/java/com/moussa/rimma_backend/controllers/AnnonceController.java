package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;
import com.moussa.rimma_backend.models.dto.FieldOption;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.services.AnnonceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Annonces", description = "Gestion des annonces des annonces publiées")
@RestController
@RequestMapping("/rimma/api/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @Operation(summary = "Récupération de toutes les annonces valides => Retourne la liste de toutes les annonces validées. Cette route est publique.")
    @GetMapping
    public ResponseEntity<Page<Annonce>> getAnnonces(Pageable pageable) {
        return ResponseEntity.ok(annonceService.getAnnonces(pageable));
    }

    /*@Operation(summary = "Récupération des annonces d’un hôte => Permet à un ADMIN toutes les annonces associées à un hôte spécifique.")
    @GetMapping("/utilisateur/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Annonce> findAnnoncesByUtilisateur(@PathVariable Long id){
        return annonceService.getAnnoncesByUtilisateur(id);
    }*/

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

    @Operation(summary = "Recupérations des types de biens disponibles en base")
    @GetMapping("/types/biens")
    @PreAuthorize("hasRole('HOTE')")
    public ResponseEntity<List<HebergementType>> getBiens() {
        return ResponseEntity.ok(Arrays.asList(HebergementType.values()));
    }

    @Operation(summary = "Recupérations des options liés à un type d'hebergement")
    @GetMapping("/options")
    @PreAuthorize("hasRole('HOTE')")
    public ResponseEntity<List<FieldOption>> getOptions(@RequestParam HebergementType type) {
        List<FieldOption> options = annonceService.getOptionsByTypeHebergement(type);
        return ResponseEntity.ok(options);
    }
}

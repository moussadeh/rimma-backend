package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.services.AnnonceService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rimma/api/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping("/utilisateur/{id}")
    public List<Annonce> findAnnoncesByUtilisateur(@PathVariable Long id){
        return annonceService.getAnnoncesByUtilisateur(id);
    }

    @PostMapping("/utilisateur/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENT') and #id == principal.id)")
    public Annonce creerAnnonce(@PathVariable Long id, @Valid @RequestBody Annonce annonce){
        return annonceService.creerAnnonce(id, annonce);
    }

    @PutMapping("/annonce/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENT') and #id == principal.id)")
    public Annonce modifierAnnonce(@PathVariable Long id, @Valid @RequestBody Annonce annonce){
        return annonceService.modifierAnnonce(id, annonce);
    }

    @PatchMapping("/annonce/{id}/desactiver")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENT') and #id == principal.id)")
    public Annonce desactiverAnnonce(@PathVariable Long id){
        return annonceService.desactiverAnnonce(id);
    }

    @PatchMapping("/annonce/{id}/activer")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENT') and #id == principal.id)")
    public Annonce activerAnnonce(@PathVariable Long id){
        return annonceService.activerAnnonce(id);
    }
}

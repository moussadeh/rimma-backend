package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rimma/api/utilisateurs")
@AllArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.trouverTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.trouverParId(id));
    }

    @PostMapping
    public ResponseEntity<Utilisateur>  creerUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.creerUtilisateur(utilisateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.modifierUtilisateur(id, utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Utilisateur> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}

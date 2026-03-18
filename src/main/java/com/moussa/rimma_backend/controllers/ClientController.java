package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Reservation;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.enums.ReservationStatusType;
import com.moussa.rimma_backend.services.AnnonceService;
import com.moussa.rimma_backend.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client", description = "Gestion des actions effectuées par un client (personne qui reserve des annonces)")
@RestController
@RequestMapping("/rimma/api/client")
public class ClientController {

    private final AnnonceService annonceService;
    private final ReservationService reservationService;

    public ClientController(AnnonceService annonceService, ReservationService reservationService) {
        this.annonceService = annonceService;
        this.reservationService = reservationService;
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

    @Operation(summary = "Permet à un client de reserver une annonce qui sera validée par l'hote.")
    @PostMapping("/me/add/reservation/{annonceId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> reserverAnnonce(Authentication authentication, @PathVariable Long annonceId) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        reservationService.reserverAnnonce(utilisateur.getId(), annonceId);
        return ResponseEntity.ok("Réservation en cours de validation");
    }

    @Operation(summary = "Permet à un client de consulter ses reservations.")
    @GetMapping("/me/reservations")
    @PreAuthorize("hasRole('CLIENT')")
    public List<Reservation> getReservationsClient(Authentication authentication) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return reservationService.getReservationsParClient(utilisateur.getId());
    }

    @Operation(summary = "Permet à un client d'annuler une reservation.")
    @DeleteMapping("/me/annuler/reservation/{reservationId}")
    @PreAuthorize("hasRole('CLIENT')")
    public String annulerReservation(Authentication authentication, @PathVariable Long reservationId) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        reservationService.annulerReservation(utilisateur.getId(), reservationId);
        return "Réservation annulée avec succès";
    }

    @GetMapping("/filter/par/statut/reservation")
    @Operation(summary = "Filtrer les reservations par Statut")
    public ResponseEntity<List<Reservation>> filterByHebergement(Authentication authentication, @RequestParam ReservationStatusType status) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        return ResponseEntity.ok(reservationService.filterByReservationStatus(utilisateur, status));
    }
}

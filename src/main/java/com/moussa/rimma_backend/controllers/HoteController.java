package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Reservation;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;
import com.moussa.rimma_backend.models.enums.ReservationStatusType;
import com.moussa.rimma_backend.services.AnnonceService;
import com.moussa.rimma_backend.services.ReservationService;
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
    private final ReservationService reservationService;

    public HoteController(AnnonceService annonceService, ReservationService reservationService) {
        this.annonceService = annonceService;
        this.reservationService = reservationService;
    }

    @Operation(summary = "Création d'une annonce => Permet à un hôte de créer une nouvelle annonce.")
    @PostMapping("/creer/annonce")
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

//    @GetMapping("/annonce/{annonceId}/reservations/me")
//    @PreAuthorize("hasRole('HOTE')")
//    @Operation(summary = "Voir les réservations d'une annonce")
//    public ResponseEntity<List<Reservation>> getReservationsAnnonce(@PathVariable Long annonceId, Authentication authentication) {
//        Utilisateur hote = (Utilisateur) authentication.getPrincipal();
//        return ResponseEntity.ok(reservationService.getReservationsAnnonce(annonceId, hote));
//    }
//
//    @PatchMapping("/valider/reservation/{reservationId}")
//    @PreAuthorize("hasRole('HOTE')")
//    @Operation(summary = "Valider une réservation")
//    public ResponseEntity<Reservation> validerReservation(@PathVariable Long reservationId, Authentication authentication) {
//        Utilisateur hote = (Utilisateur) authentication.getPrincipal();
//        return ResponseEntity.ok(reservationService.validerReservation(reservationId, hote));
//    }
//
//    @PatchMapping("/refuser/reservation/{reservationId}")
//    @PreAuthorize("hasRole('HOTE')")
//    @Operation(summary = "Refuser une réservation")
//    public ResponseEntity<Reservation> refuserReservation(@PathVariable Long reservationId, Authentication authentication) {
//        Utilisateur hote = (Utilisateur) authentication.getPrincipal();
//        return ResponseEntity.ok(reservationService.refuserReservation(reservationId, hote));
//    }
//
//    @GetMapping("/filter/reservations")
//    @PreAuthorize("hasRole('HOTE')")
//    @Operation(summary = "Filtrer les réservations des annonces de l'hôte par statut")
//    public ResponseEntity<List<Reservation>> filterReservations(Authentication authentication, @RequestParam ReservationStatusType status) {
//        Utilisateur hote = (Utilisateur) authentication.getPrincipal();
//        return ResponseEntity.ok(reservationService.filterReservationsHote(hote, status));
//    }

}

package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.exceptions.*;
import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Reservation;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.ReservationStatusType;
import com.moussa.rimma_backend.repositories.AnnonceRepository;
import com.moussa.rimma_backend.repositories.ReservationRepository;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final UtilisateurRepository utilisateurRepository;
    private final AnnonceRepository annonceRepository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(UtilisateurRepository utilisateurRepository, AnnonceRepository annonceRepository, ReservationRepository reservationRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.annonceRepository = annonceRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Reservation reserverAnnonce(Long clientId, Long annonceId) {
        Utilisateur client = utilisateurRepository.findById(clientId)
                .orElseThrow(() -> new UtilisateurNotFoundException());
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException());
        if(!annonce.getValide()) {
            throw new AnnonceNotValidException("Cette annonce n'est pas encore validée par nos équipes.");
        }
        if (reservationRepository.existsByClientAndAnnonce(client, annonce)) {
            throw new AlereadyInReservationsException("Vous avez déjà réservé cette annonce");
        }
        boolean dejaPrise = annonce.getReservations().stream()
                .anyMatch(r -> r.getStatus() == ReservationStatusType.VALIDEE);
        if (dejaPrise) {
            throw new RuntimeException("Cette annonce est déjà prise");
        }
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setAnnonce(annonce);
        reservation.setStatus(ReservationStatusType.EN_COURS_DE_VALIDATION);
        reservation.setDateReservation(LocalDateTime.now());

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation annulerReservation(Long clientId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundReservationException("Réservation non trouvée"));
        reservation.setStatus(ReservationStatusType.ANNULEE);
        reservation.setDateAnnulationClient(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsParClient(Long clientId) {
        Utilisateur client = utilisateurRepository.findById(clientId)
                .orElseThrow(() -> new UtilisateurNotFoundException());
        return reservationRepository.findByClient(client);
    }

    @Override
    public List<Reservation> filterByReservationStatus(ReservationStatusType status) {
        if (status == null) {
            throw new IllegalArgumentException("Le status d'hébergement est requis");
        }
        return reservationRepository.findByReservationStatusType(status);
    }

}

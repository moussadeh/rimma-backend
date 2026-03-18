package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Reservation;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.enums.ReservationStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByClient(Utilisateur client);

    boolean existsByClientAndAnnonce(Utilisateur client, Annonce annonce);

    List<Reservation> findByClientAndStatus(Utilisateur client, ReservationStatusType status);

//    List<Reservation> findByAnnonceUtilisateurAndStatus(Utilisateur hote, ReservationStatusType status);
//
//    List<Reservation> findByAnnonceIdAndAnnonceUtilisateur(Long annonceId, Utilisateur utilisateur);
//
//    List<Reservation> findByAnnonceUtilisateur(Utilisateur hote);
}

package com.moussa.rimma_backend.services;

import com.moussa.rimma_backend.models.Reservation;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.enums.ReservationStatusType;

import java.util.List;

public interface ReservationService {

    Reservation reserverAnnonce(Long clientId, Long annonceId);

    Reservation annulerReservation(Long clientId, Long reservationId);

    List<Reservation> getReservationsParClient(Long clientId);

    List<Reservation> filterByReservationStatus(Utilisateur client, ReservationStatusType status);
}

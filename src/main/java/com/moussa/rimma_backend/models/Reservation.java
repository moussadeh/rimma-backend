package com.moussa.rimma_backend.models;

import com.moussa.rimma_backend.models.enums.ReservationStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Utilisateur client;

    @ManyToOne
    @JoinColumn(name = "annonce_id", nullable = false)
    private Annonce annonce;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ReservationStatusType status;

    private LocalDateTime dateReservation;

    private LocalDateTime dateReponseHote;

    private LocalDateTime dateAnnulationClient;
}

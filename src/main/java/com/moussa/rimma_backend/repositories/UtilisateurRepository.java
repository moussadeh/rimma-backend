package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository {

    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findByNomAndPrenom(String nom, String prenom);

    Optional<Utilisateur> findByTelephone(String telephone);

    boolean existsByEmail(String email);
}

package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findByNomAndPrenom(String nom, String prenom);

    Optional<Utilisateur> findByTelephone(String telephone);

    boolean existsByEmail(String email);

}

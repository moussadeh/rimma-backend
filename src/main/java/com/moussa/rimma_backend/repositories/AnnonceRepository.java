package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    List<Annonce> findByUtilisateurId(Long id);

    List<Annonce> findByHebergement(HebergementType hebergement);

    List<Annonce> findByStatut(StatutType statut);
}

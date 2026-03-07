package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    List<Annonce> findByUtilisateurId(Long id);

    List<Annonce> findByHebergement(HebergementType hebergement);

    List<Annonce> findByStatut(StatutType statut);

    List<Annonce> findByValideTrue();

    boolean existsByIdAndUtilisateurId(Long id, Long utilisateurId);

    @Query("""
        SELECT a FROM Annonce a
        WHERE a.valide = true
        AND (:titre IS NULL OR a.titre ILIKE %:titre%)
        AND (:ville IS NULL OR a.ville ILIKE %:ville%)
        AND (:quartier IS NULL OR a.quartier ILIKE %:quartier%)
    """)
    List<Annonce> searchAnnonces(String titre, String ville, String quartier);
}

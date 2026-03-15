package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    List<Annonce> findByUtilisateurId(Long id);

    List<Annonce> findByHebergement(HebergementType hebergement);

    List<Annonce> findByStatut(StatutType statut);

    Page<Annonce> findByValideTrue(Pageable pageable);

    boolean existsByIdAndUtilisateurId(Long id, Long utilisateurId);

    @Query("""
        SELECT a FROM Annonce a
        WHERE a.valide = true
        AND (
            a.titre ILIKE %:query%
            OR a.ville ILIKE %:query%
            OR a.quartier ILIKE %:query%
            OR a.description ILIKE %:query%
        )
    """)
    List<Annonce> searchAnnonces(String query);

    @Query("""
        SELECT a FROM Annonce a
        WHERE a.utilisateur.id = :userId
        AND (
            a.titre ILIKE %:query%
            OR a.ville ILIKE %:query%
            OR a.quartier ILIKE %:query%
            OR a.description ILIKE %:query%
        )
    """)
    List<Annonce> searchMyOwnAnnonces(Long userId, String query);

    List<Annonce> findByHebergementAndValideTrue(HebergementType type);

    @Query("""
        SELECT a FROM Annonce a
        WHERE a.valide = true
        AND (:minPrix IS NULL OR a.prix >= :minPrix)
        AND (:maxPrix IS NULL OR a.prix <= :maxPrix)
    """)
    List<Annonce> filterByPrix(Double minPrix, Double maxPrix);

}

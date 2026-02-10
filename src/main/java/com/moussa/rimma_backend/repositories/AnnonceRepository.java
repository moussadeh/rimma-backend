package com.moussa.rimma_backend.repositories;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;

import java.util.List;

public interface AnnonceRepository {

    List<Annonce> findByUtilisateurId(Long id);

    List<Annonce> findByHebergement(HebergementType hebergement);

    List<Annonce> findByStatut(StatutType statut);
}

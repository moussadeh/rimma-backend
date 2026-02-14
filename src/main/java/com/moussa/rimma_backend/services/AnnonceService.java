package com.moussa.rimma_backend.services;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;

import java.util.List;

public interface AnnonceService {

    List<Annonce> getAnnoncesByUtilisateur(Long utilisateurId);

    Annonce creerAnnonce(Long utilisateurId, AnnonceRequest annonceRequest);

    Annonce modifierAnnonce(Long annonceId, AnnonceRequest annonceRequest);

    Annonce desactiverAnnonce(Long annonceId);

    Annonce activerAnnonce(Long annonceId);

    boolean isOwner(Long annonceId, Long userId);
}

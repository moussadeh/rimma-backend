package com.moussa.rimma_backend.services;

import com.moussa.rimma_backend.models.Annonce;

import java.util.List;

public interface AnnonceService {

    List<Annonce> getAnnoncesByUtilisateur(Long utilisateurId);

    Annonce creerAnnonce(Long utilisateurId, Annonce annonce);

    Annonce modifierAnnonce(Long annonceId, Annonce annonce);

    Annonce desactiverAnnonce(Long annonceId);

    Annonce activerAnnonce(Long annonceId);
}

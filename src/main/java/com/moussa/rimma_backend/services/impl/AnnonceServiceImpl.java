package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.repositories.AnnonceRepository;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.AnnonceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;

    public AnnonceServiceImpl(AnnonceRepository annonceRepository, UtilisateurRepository utilisateurRepository) {
        this.annonceRepository = annonceRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Annonce> getAnnoncesByUtilisateur(Long utilisateurId) {
        return List.of();
    }

    @Override
    public Annonce creerAnnonce(Long utilisateurId, Annonce annonce) {
        return null;
    }

    @Override
    public Annonce modifierAnnonce(Long annonceId, Annonce annonce) {
        return null;
    }

    @Override
    public void desactiverAnnonce(Long annonceId) {

    }
}

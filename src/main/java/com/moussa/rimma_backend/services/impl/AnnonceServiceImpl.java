package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.exceptions.AnnonceNotFoundException;
import com.moussa.rimma_backend.exceptions.UtilisateurNotFoundException;
import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.enums.StatutType;
import com.moussa.rimma_backend.repositories.AnnonceRepository;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.AnnonceService;
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
    public List<Annonce> getAnnoncesByUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException(id));

        return annonceRepository.findByUtilisateurId(id);
    }

    @Override
    public Annonce creerAnnonce(Long id, Annonce annonce) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException(id));

        annonce.setUtilisateur(utilisateur);
        annonce.setActif(true);

        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce modifierAnnonce(Long annonceId, Annonce annonce) {
        Annonce existingAnnonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException(annonceId));

        existingAnnonce.setTitre(annonce.getTitre());
        existingAnnonce.setDescription(annonce.getDescription());
        existingAnnonce.setPrix(annonce.getPrix());
        existingAnnonce.setVille(annonce.getVille());
        existingAnnonce.setQuartier(annonce.getQuartier());
        existingAnnonce.setStatut(annonce.getStatut());
        existingAnnonce.setHebergement(annonce.getHebergement());
        existingAnnonce.setActif(true);

        return annonceRepository.save(existingAnnonce);
    }

    @Override
    public Annonce desactiverAnnonce(Long annonceId) {
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException(annonceId));

        annonce.setActif(false);
        annonce.setStatut(StatutType.DESACTIVEE);

        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce activerAnnonce(Long annonceId) {
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException(annonceId));

        annonce.setActif(true);
        annonce.setStatut(StatutType.ACTIVE);

        return annonceRepository.save(annonce);
    }
}

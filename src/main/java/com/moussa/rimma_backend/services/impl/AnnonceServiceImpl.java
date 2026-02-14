package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.exceptions.AnnonceNotFoundException;
import com.moussa.rimma_backend.exceptions.ImageLimitException;
import com.moussa.rimma_backend.exceptions.UtilisateurNotFoundException;
import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Image;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;
import com.moussa.rimma_backend.models.enums.StatutType;
import com.moussa.rimma_backend.repositories.AnnonceRepository;
import com.moussa.rimma_backend.repositories.ImageRepository;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.AnnonceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("annonceService")
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
    public Annonce creerAnnonce(Long id, AnnonceRequest annonceRequest) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException(id));

        List<String> urls_images = annonceRequest.getImages();

        if (urls_images == null || urls_images.isEmpty() || urls_images.size() > 10) {
            throw new ImageLimitException();
        }

        List<Image> images = new ArrayList<>();

        Annonce annonce = new Annonce();
        annonce.setTitre(annonceRequest.getTitre());
        annonce.setDescription(annonceRequest.getDescription());
        annonce.setPrix(annonceRequest.getPrix());
        annonce.setVille(annonceRequest.getVille());
        annonce.setQuartier(annonceRequest.getQuartier());
        annonce.setHebergement(annonceRequest.getHebergement());
        annonce.setStatut(annonceRequest.getStatut());
        annonce.setUtilisateur(utilisateur);
        annonce.setActif(true);

        urls_images.forEach(url -> {
            Image image = new Image();
            image.setUrl(url);
            image.setAnnonce(annonce);
            images.add(image);
        });

        annonce.setImages(images);

        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce modifierAnnonce(Long annonceId, AnnonceRequest annonceRequest) {
        Annonce existingAnnonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException(annonceId));

        existingAnnonce.setTitre(annonceRequest.getTitre());
        existingAnnonce.setDescription(annonceRequest.getDescription());
        existingAnnonce.setPrix(annonceRequest.getPrix());
        existingAnnonce.setVille(annonceRequest.getVille());
        existingAnnonce.setQuartier(annonceRequest.getQuartier());
        existingAnnonce.setStatut(annonceRequest.getStatut());
        existingAnnonce.setHebergement(annonceRequest.getHebergement());
        existingAnnonce.setActif(true);

        List<String> urls_images = annonceRequest.getImages();

        if (urls_images == null || urls_images.isEmpty() || urls_images.size() > 10) {
            throw new ImageLimitException();
        }

        existingAnnonce.getImages().clear();

        urls_images.forEach(url -> {
            Image img = new Image();
            img.setUrl(url);
            img.setAnnonce(existingAnnonce);
            existingAnnonce.getImages().add(img);
        });

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

    @Override
    public boolean isOwner(Long annonceId, Long userId) {
        return annonceRepository.existsByIdAndUtilisateurId(annonceId, userId);
    }
}

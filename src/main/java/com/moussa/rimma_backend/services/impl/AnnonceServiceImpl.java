package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.exceptions.AnnonceNotFoundException;
import com.moussa.rimma_backend.exceptions.ImageLimitException;
import com.moussa.rimma_backend.exceptions.SearchParamMissingException;
import com.moussa.rimma_backend.exceptions.UtilisateurNotFoundException;
import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.Image;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;
import com.moussa.rimma_backend.models.enums.HebergementType;
import com.moussa.rimma_backend.models.enums.StatutType;
import com.moussa.rimma_backend.repositories.AnnonceRepository;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.AnnonceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new UtilisateurNotFoundException());

        return annonceRepository.findByUtilisateurId(id);
    }

    @Override
    public Annonce creerAnnonce(Long id, AnnonceRequest annonceRequest) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException());

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
                .orElseThrow(() -> new AnnonceNotFoundException());

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
                .orElseThrow(() -> new AnnonceNotFoundException());

        annonce.setActif(false);
        annonce.setStatut(StatutType.DESACTIVEE);

        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce activerAnnonce(Long annonceId) {
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException());

        annonce.setActif(true);
        annonce.setStatut(StatutType.ACTIVE);

        return annonceRepository.save(annonce);
    }

    @Override
    public boolean isOwner(Long annonceId, Long userId) {
        return annonceRepository.existsByIdAndUtilisateurId(annonceId, userId);
    }

    @Override
    public Page<Annonce> getAnnonces(Pageable pageable){
        return annonceRepository.findByValideTrue(pageable);
    }

    @Override
    public Annonce validerAnnonce(Long annonceId) {
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException());

        annonce.setValide(true);

        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce invaliderAnnonce(Long annonceId) {
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException());

        annonce.setValide(true);

        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce getAnnonceById(Long id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new AnnonceNotFoundException());
    }

    @Override
    public List<Annonce> searchAnnonces(String query) {
        if (query == null || query.isBlank())
            throw new SearchParamMissingException();

        return annonceRepository.searchAnnonces(query);
    }

    @Override
    public List<Annonce> searchMyOwnAnnonces(Long userId, String query) {
        if (query == null || query.isBlank()) {
            throw new SearchParamMissingException();
        }

        return annonceRepository.searchMyOwnAnnonces(userId, query.trim());
    }

    @Override
    public List<Annonce> filterByHebergement(HebergementType type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type d'hébergement est requis");
        }
        return annonceRepository.findByHebergementAndValideTrue(type);
    }

    @Override
    public List<Annonce> filterByPrix(Double minPrix, Double maxPrix) {
        if (minPrix == null && maxPrix == null) {
            throw new IllegalArgumentException("Au moins un prix doit être fourni");
        }
        return annonceRepository.filterByPrix(minPrix, maxPrix);
    }

    @Override
    public void ajouterFavori(Long utilisateurId, Long annonceId) {
        Utilisateur user = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException());
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException());
        if(!annonce.getValide()) {
            throw new RuntimeException("Cette annonce n'est pas encore validée par nos équipes.");
        }
        if (user.getFavoris().contains(annonce)) {
            throw new RuntimeException("Cette annonce est déjà dans vos favoris");
        }
        user.getFavoris().add(annonce);
        utilisateurRepository.save(user);
    }

    @Override
    public void retirerFavori(Long utilisateurId, Long annonceId) {
        Utilisateur user = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException());
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotFoundException());
        if (!user.getFavoris().contains(annonce)) {
            throw new RuntimeException("Cette annonce n'est pas dans vos favoris");
        }
        user.getFavoris().remove(annonce);
        utilisateurRepository.save(user);
    }

    @Override
    public List<Annonce> getFavoris(Long utilisateurId) {
        Utilisateur user = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException());
        return user.getFavoris();
    }

//    @Override
//    public AnnonceResponse fabriquerAnnonceResponse(Annonce annonce) {
//        AnnonceResponse annonceResponse = new AnnonceResponse();
//        annonceResponse.setTitre(annonce.getTitre());
//        annonceResponse.setDescription(annonce.getDescription());
//        annonceResponse.setPrix(annonce.getPrix());
//        annonceResponse.setVille(annonce.getVille());
//        annonceResponse.setQuartier(annonce.getQuartier());
//        annonceResponse.setHebergement(annonce.getHebergement());
//        annonceResponse.setValide(annonce.getValide());
//        annonceResponse.setActif(annonce.getActif());
//        annonceResponse.setStatut(annonce.getStatut());
//
//        Utilisateur utilisateur = annonce.getUtilisateur();
//        annonceResponse.setUtilisateur(utilisateur.toString());
//
//        List<String> images = new ArrayList<>();
//        for(Image image : annonce.getImages()) {
//            images.add(image.getUrl());
//        }
//        annonceResponse.setImages(images);
//
//        return annonceResponse;
//    }

}

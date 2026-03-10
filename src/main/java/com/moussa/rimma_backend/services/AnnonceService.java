package com.moussa.rimma_backend.services;

import com.moussa.rimma_backend.models.Annonce;
import com.moussa.rimma_backend.models.dto.AnnonceRequest;
import com.moussa.rimma_backend.models.enums.HebergementType;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface AnnonceService {

    List<Annonce> getAnnoncesByUtilisateur(Long utilisateurId);

    Annonce creerAnnonce(Long utilisateurId, AnnonceRequest annonceRequest);

    Annonce modifierAnnonce(Long annonceId, AnnonceRequest annonceRequest);

    Annonce desactiverAnnonce(Long annonceId);

    Annonce activerAnnonce(Long annonceId);

    boolean isOwner(Long annonceId, Long userId);

    List<Annonce> getAnnonces();

    Annonce validerAnnonce(Long annonceId);

    Annonce invaliderAnnonce(Long annonceId);

    Annonce getAnnonceById(Long id);


    List<Annonce> searchAnnonces(String query);

    List<Annonce> searchMyOwnAnnonces(Long userId, String query);

    List<Annonce> filterByHebergement(HebergementType type);

    List<Annonce> filterByPrix(Double minPrix, Double maxPrix);
}

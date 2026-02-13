package com.moussa.rimma_backend.services;

import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.UtilisateurRequest;

import java.util.List;

public interface UtilisateurService {

    Utilisateur creerUtilisateur(UtilisateurRequest utilisateurRequest);

    Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur);

    Utilisateur trouverParId(Long id);

    List<Utilisateur> trouverTous();

    void supprimerUtilisateur(Long id);
}

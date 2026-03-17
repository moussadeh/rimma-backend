package com.moussa.rimma_backend.services;

import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.UtilisateurRequest;
import com.moussa.rimma_backend.models.dto.UtilisateurResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UtilisateurService {

    Utilisateur creerUtilisateur(UtilisateurRequest utilisateurRequest);

    Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur);

    Utilisateur trouverParId(Long id);

    UtilisateurResponse utilisateurConnectee(Utilisateur utilisateur);

    Page<UtilisateurResponse> trouverTous(Pageable pageable);

    void supprimerUtilisateur(Long id);

    Utilisateur trouverParEmail(String email);
}

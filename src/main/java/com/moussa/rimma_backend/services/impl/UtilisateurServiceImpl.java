package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur trouverParId(Long id) {
        return null;
    }

    @Override
    public List<Utilisateur> trouverTous() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void supprimerUtilisateur(Long id) {

    }
}

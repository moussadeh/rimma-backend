package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.configurations.SecurityConfig;
import com.moussa.rimma_backend.exceptions.EmailAlreadyUsedException;
import com.moussa.rimma_backend.exceptions.UtilisateurNotFoundException;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.UtilisateurService;
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
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new EmailAlreadyUsedException(utilisateur.getEmail());
        }

        utilisateur.setActive(true);

        SecurityConfig securityConfig = new SecurityConfig();
        utilisateur.setMotDePasse(securityConfig.passwordEncoder().encode(utilisateur.getMotDePasse()));

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur existingUtilisateur = trouverParId(id);

        if (utilisateurRepository.existsByEmail(existingUtilisateur.getEmail())) {
            throw new EmailAlreadyUsedException(existingUtilisateur.getEmail());
        }

        existingUtilisateur.setNom(utilisateur.getNom());
        existingUtilisateur.setPrenom(utilisateur.getPrenom());
        existingUtilisateur.setTelephone(utilisateur.getTelephone());
        existingUtilisateur.setEmail(utilisateur.getEmail());
        existingUtilisateur.setRole(utilisateur.getRole());
        existingUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
        existingUtilisateur.setActive(true);

        return utilisateurRepository.save(existingUtilisateur);
    }

    @Override
    public Utilisateur trouverParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException(id));
    }

    @Override
    public List<Utilisateur> trouverTous() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        Utilisateur utilisateur = trouverParId(id);
        utilisateurRepository.delete(utilisateur);
    }
}

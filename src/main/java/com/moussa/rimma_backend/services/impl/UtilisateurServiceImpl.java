package com.moussa.rimma_backend.services.impl;

import com.moussa.rimma_backend.configurations.SecurityConfig;
import com.moussa.rimma_backend.exceptions.AlereadyHasThisRoleException;
import com.moussa.rimma_backend.exceptions.EmailAlreadyUsedException;
import com.moussa.rimma_backend.exceptions.UtilisateurNotFoundException;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.UtilisateurRequest;
import com.moussa.rimma_backend.models.dto.UtilisateurResponse;
import com.moussa.rimma_backend.models.enums.RoleType;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.services.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilisateur creerUtilisateur(UtilisateurRequest utilisateurRequest) {
        if (utilisateurRepository.existsByEmail(utilisateurRequest.getEmail())) {
            throw new EmailAlreadyUsedException(utilisateurRequest.getEmail());
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurRequest.getNom());
        utilisateur.setPrenom(utilisateurRequest.getPrenom());
        utilisateur.setTelephone(utilisateurRequest.getTelephone());
        //utilisateur.setRole(utilisateurRequest.getRole());
        utilisateur.setEmail(utilisateurRequest.getEmail());
        utilisateur.setActive(true);
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurRequest.getMotDePasse()));

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
        //existingUtilisateur.setRole(utilisateur.getRole());
        existingUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
        existingUtilisateur.setActive(true);

        return utilisateurRepository.save(existingUtilisateur);
    }

    @Override
    public Utilisateur trouverParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException());
    }

    @Override
    public UtilisateurResponse utilisateurConnectee(Utilisateur utilisateur) {
        UtilisateurResponse response = new UtilisateurResponse();
        response.setNom(utilisateur.getNom());
        response.setPrenom(utilisateur.getPrenom());
        response.setEmail(utilisateur.getEmail());
        response.setRoles(utilisateur.getRoles());
        response.setTelephone(utilisateur.getTelephone());
        response.setActive(utilisateur.getActive());

        return response;
    }

    @Override
    public Page<UtilisateurResponse> trouverTous(Pageable pageable) {
        Page<Utilisateur> utilisateurs = utilisateurRepository.findAll(pageable);
        return utilisateurs.map(utilisateur -> {
            UtilisateurResponse response = new UtilisateurResponse();
            response.setNom(utilisateur.getNom());
            response.setPrenom(utilisateur.getPrenom());
            response.setEmail(utilisateur.getEmail());
            response.setTelephone(utilisateur.getTelephone());
            response.setRoles(utilisateur.getRoles());
            response.setActive(utilisateur.getActive());
            return response;
        });
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        Utilisateur utilisateur = trouverParId(id);
        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public Utilisateur trouverParEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UtilisateurNotFoundException());
    }

    @Override
    public void ajouterRole(Long userId, RoleType role) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(UtilisateurNotFoundException::new);

        if (user.getRoles().contains(role)) {
            throw new AlereadyHasThisRoleException("L'utilisateur possède déjà ce rôle");
        }

        user.getRoles().add(role);

        utilisateurRepository.save(user);
    }
}

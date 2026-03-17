package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.exceptions.EmailAlreadyUsedException;
import com.moussa.rimma_backend.exceptions.IncorrectPasswordException;
import com.moussa.rimma_backend.exceptions.UtilisateurWithEmailNotFoundException;
import com.moussa.rimma_backend.models.Utilisateur;
import com.moussa.rimma_backend.models.dto.LoginRequest;
import com.moussa.rimma_backend.models.dto.LoginResponse;
import com.moussa.rimma_backend.models.dto.RegisterRequest;
import com.moussa.rimma_backend.models.dto.RegisterResponse;
import com.moussa.rimma_backend.models.enums.RoleType;
import com.moussa.rimma_backend.repositories.UtilisateurRepository;
import com.moussa.rimma_backend.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentification", description = "Gestion de l'authentification et de l'inscription des utilisateurs")
@RestController
@RequestMapping("/rimma/auth")
public class AuthController {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;

    public AuthController(JwtService jwtService, PasswordEncoder passwordEncoder, UtilisateurRepository utilisateurRepository) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Operation(summary = "Authentification d'un utilisateur => Authentifie un utilisateur avec email et mot de passe. Retourne un token JWT si les identifiants sont valides.")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UtilisateurWithEmailNotFoundException(request.getEmail()));

        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new IncorrectPasswordException();
        }

        String token = jwtService.generateToken(utilisateur);

        return new LoginResponse(token);
    }

    @Operation(summary = "Inscription d'un client => Permet de créer un nouveau compte client avec le rôle CLIENT. L'email doit être unique.")
    @PostMapping("/client/register")
    public RegisterResponse registerClient(@RequestBody RegisterRequest request) {
        if (utilisateurRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException(request.getEmail());
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setTelephone(request.getTelephone());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setRole(RoleType.ROLE_CLIENT);
        utilisateur.setActive(true);

        utilisateurRepository.save(utilisateur);

        return new RegisterResponse("Compte client créé avec succés : " + utilisateur.getEmail());
    }

    @Operation(summary = "Inscription d'un hôte => Permet de créer un nouveau compte hôte avec le rôle HÔTE. L'email doit être unique.")
    @PostMapping("/hote/register")
    public RegisterResponse registerHote(@RequestBody RegisterRequest request) {
        if (utilisateurRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException(request.getEmail());
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setTelephone(request.getTelephone());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setRole(RoleType.ROLE_HOTE);
        utilisateur.setActive(true);

        utilisateurRepository.save(utilisateur);

        return new RegisterResponse("Compte hôte créé avec succés : " + utilisateur.getEmail());
    }
}

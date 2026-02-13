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
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UtilisateurWithEmailNotFoundException(request.getEmail()));

        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(utilisateur);

        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
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

        return new RegisterResponse("Compte créé avec succés : " + utilisateur.getEmail());
    }
}

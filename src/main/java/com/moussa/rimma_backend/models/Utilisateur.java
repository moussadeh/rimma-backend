package com.moussa.rimma_backend.models;

import com.moussa.rimma_backend.models.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "utilisateurs")
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String telephone;
    private Boolean active = Boolean.TRUE;

    @Column(unique = true)
    private String email;

    private String motDePasse;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType role;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Annonce> annonces;
}

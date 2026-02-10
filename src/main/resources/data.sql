-- Vider la table avant insertion
DELETE FROM utilisateurs;

-- Réinitialiser l'auto-increment
ALTER TABLE utilisateurs AUTO_INCREMENT = 1;

INSERT INTO utilisateurs (nom, prenom, email, telephone, mot_de_passe, role, active) VALUES
    ('admin', 'admin', 'admin@admin.com', '770000001', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_ADMIN', true),
    ('client', 'client', 'client@client.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    ('annonceur', 'annonceur', 'annonceur@annonceur.com', '770000003', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_ANNONCEUR', true);
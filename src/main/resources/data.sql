TRUNCATE TABLE utilisateurs RESTART IDENTITY CASCADE;

INSERT INTO utilisateurs (nom, prenom, email, telephone, mot_de_passe, role, active) VALUES
    ('admin', 'admin', 'admin@admin.com', '770000001', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_ADMIN', true),
    ('moussa', 'moussa', 'moussa@moussa.com', '770000003', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    ('client', 'client', 'client@client.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    ('client1', 'client1', 'client1@client1.com', '770000004', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    ('client2', 'client2', 'client2@client2.com', '770000005', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    ('client3', 'client3', 'client3@client3.com', '770000006', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true);
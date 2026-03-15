TRUNCATE TABLE utilisateurs RESTART IDENTITY CASCADE;

INSERT INTO utilisateurs (nom, prenom, email, telephone, mot_de_passe, role, active) VALUES
    ('moussa', 'moussa', 'moussa@moussa.com', '000000000', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    ('ib', 'ib', 'ib@ib.com', '000000000', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_ADMIN', true),
    ('atf', 'atf', 'atf@atf.com', '00000000', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_ADMIN', true),
    --('client', 'client', 'client@client.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    --('client1', 'client1', 'client1@client1.com', '770000004', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    --('client2', 'client2', 'client2@client2.com', '770000005', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    --('client3', 'client3', 'client3@client3.com', '770000006', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_CLIENT', true),
    --('admin', 'admin', 'admin@admin.com', '770000001', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', 'ROLE_ADMIN', true)
    ;
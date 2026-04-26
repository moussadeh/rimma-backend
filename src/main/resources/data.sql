TRUNCATE TABLE reservations RESTART IDENTITY CASCADE;
TRUNCATE TABLE favoris RESTART IDENTITY CASCADE;
TRUNCATE TABLE images RESTART IDENTITY CASCADE;
TRUNCATE TABLE annonces RESTART IDENTITY CASCADE;
TRUNCATE TABLE utilisateurs RESTART IDENTITY CASCADE;

-- ===============================
-- UTILISATEURS
-- ===============================
INSERT INTO utilisateurs (nom, prenom, email, telephone, mot_de_passe, active) VALUES
    ('admin', 'admin', 'admin@admin.com', '770000001', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', true),
    ('hote', 'hote', 'hote@hote.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', true),
    ('hote2', 'hote2', 'hote2@hote2.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', true),
    ('hote3', 'hote3', 'hote3@hote3.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', true),
    ('client', 'client', 'client@client.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', true),
    ('client2', 'client2', 'client2@client2.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', true),
    ('client3', 'client3', 'client3@client3.com', '770000002', '$2a$12$B96IdtAD8Th5FvTPRoMKruUHqAU0DIqPLsS/R4yitrg911xD4xBNa', true)
;

-- ===============================
-- ROLES
-- ===============================
INSERT INTO utilisateur_roles (utilisateur_id, role) VALUES
    (1, 'ROLE_ADMIN'),
    (1, 'ROLE_CLIENT'),
    (1, 'ROLE_HOTE'),
    (2, 'ROLE_HOTE'),
    (2, 'ROLE_CLIENT'),
    (3, 'ROLE_HOTE'),
    (4, 'ROLE_HOTE'),
    (5, 'ROLE_HOTE'),
    (5, 'ROLE_CLIENT'),
    (6, 'ROLE_CLIENT'),
    (7, 'ROLE_CLIENT')
;


-- ===============================
-- ANNONCES
-- ===============================
INSERT INTO annonces (titre, description, prix, ville, quartier, actif, valide, hebergement, statut, utilisateur_id) VALUES
    ('Appartement moderne', 'Bel appartement bien équipé', 25000, 'Nouakchott', 'Tevragh Zeina', true, true, 'APPARTEMENT', 'ACTIVE', 2),
    ('Maison avec jardin', 'Grande maison familiale', 45000, 'Nouadhibou', 'Numerowat', true, true, 'MAISON', 'ACTIVE', 2),
    ('Studio meublé', 'Studio idéal pour étudiant', 15000, 'Nouakchott', 'Capitale', true, true, 'APPARTEMENT', 'ACTIVE', 2),
    ('Magasin commercial', 'Local commercial bien situé', 80000, 'Rosso', 'Centre', true, true, 'MAGASIN', 'ACTIVE', 2),
    ('Terrain constructible', 'Terrain de 300m²', 120000, 'Nouakchott', 'PK9', true, true, 'TERRAIN', 'ACTIVE', 2),
    ('Boutique équipée', 'Boutique prête à l’usage', 60000, 'Nouakchott', 'Madrid', true, true, 'BOUTIQUE', 'ACTIVE', 2),
    ('Appartement spacieux', 'Appartement lumineux proche des commerces', 22000, 'Nouakchott', 'Sebkha', true, true, 'APPARTEMENT', 'ACTIVE', 2),
    ('Studio moderne', 'Studio meublé prêt à habiter', 17000, 'Nouadhibou', 'Numerowat', true, true, 'APPARTEMENT', 'ACTIVE', 3),
    ('Maison traditionnelle', 'Maison confortable dans quartier calme', 40000, 'Rosso', 'Centre', true, true, 'MAISON', 'ACTIVE', 3),
    ('Studio simple', 'Studio pratique pour une personne', 12000, 'Nouakchott', 'Ksar', true, true, 'APPARTEMENT', 'ACTIVE', 3),
    ('Magasin au centre ville', 'Magasin idéal pour commerce', 70000, 'Nouadhibou', 'Centre', true, true, 'MAGASIN', 'ACTIVE', 3),
    ('Terrain à vendre', 'Terrain de 500m² dans une zone en développement', 95000, 'Nouakchott', 'Dar Naim', true, true, 'TERRAIN', 'ACTIVE', 3),
    ('Boutique commerciale', 'Boutique bien placée au marché', 50000, 'Nouakchott', 'Sebkha', true, true, 'BOUTIQUE', 'ACTIVE', 4),
    ('Appartement familial', 'Grand appartement pour famille', 30000, 'Nouakchott', 'Arafat', true, true, 'APPARTEMENT', 'ACTIVE', 4),
    ('Maison moderne', 'Maison moderne avec cour et parking', 55000, 'Nouakchott', 'Tevragh Zeina', true, true, 'MAISON', 'ACTIVE', 4)
;

-- ===============================
-- IMAGES
-- ===============================
INSERT INTO images (id, url, annonce_id) VALUES
    (gen_random_uuid(), 'https://images.unsplash.com/photo-1560448204-e02f11c3d0e2', 1),
    (gen_random_uuid(), 'https://images.unsplash.com/photo-1560448075-bb485b067938', 4),
    (gen_random_uuid(), 'https://images.unsplash.com/photo-1572120360610-d971b9d7767c', 2),
    (gen_random_uuid(), 'https://images.unsplash.com/photo-1507089947368-19c1da9775ae', 3),
    (gen_random_uuid(), 'https://images.unsplash.com/photo-1497366216548-37526070297c', 4),
    (gen_random_uuid(), 'https://images.unsplash.com/photo-1501183638710-841dd1904471', 2),
    (gen_random_uuid(), 'https://images.unsplash.com/photo-1556911220-bda9f7f7597e', 3)
;

-- ===============================
-- FAVORIS
-- ===============================
--INSERT INTO favoris (utilisateur_id, annonce_id) VALUES
    --(5,1),
    --(5,2),
    --(6,3),
    --(6,1),
    --(7,4),
    --(7,2),
    --(5,2)
--;

-- ===============================
-- RESERVATIONS
-- ===============================
--INSERT INTO reservations (client_id, annonce_id, status, date_reservation, date_reponse_hote, date_annulation_client) VALUES
    --(5,1,'EN_COURS_DE_VALIDATION', NOW(), NULL, NULL),
    --(5,2,'EN_COURS_DE_VALIDATION', NOW() - INTERVAL '2 days', NULL, NULL),
    --(6,3,'EN_COURS_DE_VALIDATION', NOW() - INTERVAL '3 days', NULL, NULL),
    --(6,4,'EN_COURS_DE_VALIDATION', NOW() - INTERVAL '5 days', NULL, NULL),
    --(7,5,'EN_COURS_DE_VALIDATION', NOW() - INTERVAL '6 days', NULL, NULL)
--;
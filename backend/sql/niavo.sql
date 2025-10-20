-- DROP et RECREATE SCHEMA
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- Table Departements
CREATE TABLE departements (
    id_departement SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

-- Table Employes
CREATE TABLE employes (
    id_employe SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    id_departement INT REFERENCES departements(id_departement) ON DELETE SET NULL
);



-- Type enum des catégories de recettes
CREATE TYPE categorie_budget AS ENUM ('impot','douane','non_fiscal','dons');

-- Table des exercices budgétaires
CREATE TABLE exercice (
    id_exercice      SERIAL PRIMARY KEY,
    annee            INTEGER NOT NULL UNIQUE,
    note             TEXT
);

-- Catégories de recettes
CREATE TABLE categorie (
    id_categorie  SERIAL PRIMARY KEY,
    code          categorie_budget NOT NULL UNIQUE,
    libelle       TEXT NOT NULL
);

-- Natures de recettes
CREATE TABLE nature_recette (
    id_nature_recette  SERIAL PRIMARY KEY,
    id_categorie       INTEGER NOT NULL REFERENCES categorie(id_categorie) ON DELETE RESTRICT,
    code               VARCHAR(50),
    libelle            TEXT NOT NULL,
    description        TEXT
);

-- Recettes par nature et par année
CREATE TABLE recette (
    id_recette          SERIAL PRIMARY KEY,
    id_nature_recette   INTEGER NOT NULL REFERENCES nature_recette(id_nature_recette) ON DELETE CASCADE,
    id_exercice         INTEGER NOT NULL REFERENCES exercice(id_exercice) ON DELETE CASCADE,
    montant             NUMERIC(14,2) NOT NULL DEFAULT 0.00,
    commentaire         TEXT,
    UNIQUE (id_nature_recette, id_exercice)
);

-- Détails pour les dons
CREATE TABLE detail_don (
    id_detail_don   SERIAL PRIMARY KEY,
    id_recette      INTEGER NOT NULL REFERENCES recette(id_recette) ON DELETE CASCADE,
    type_don        VARCHAR(50) NOT NULL,
    montant         NUMERIC(14,2) NOT NULL DEFAULT 0.00
);

------------------------------------
-- INSERTION DES DONNÉES
------------------------------------

-- Exercices budgétaires
INSERT INTO exercice (annee, note) VALUES (2024,'LFR 2024'), (2025,'LF 2025');

-- Catégories
INSERT INTO categorie (code, libelle) VALUES
 ('impot','Recettes fiscales intérieures'),
 ('douane','Recettes douanières'),
 ('non_fiscal','Recettes non fiscales'),
 ('dons','Dons');

-- Natures fiscales
INSERT INTO nature_recette (id_categorie, code, libelle) VALUES
 (1,'IMP_REV','Impôt sur les revenus'),
 (1,'IMP_REV_SAL','Impôt sur les revenus salariaux et assimilés'),
 (1,'IMP_CAPMO','Impôt sur les revenus des capitaux mobiliers'),
 (1,'IMP_TVA','Taxe sur la valeur ajoutée (y compris TTM)'),
 (1,'DROIT_ACC','Droit d''accise'),
 (1,'AUTRES_FISC','Autres recettes fiscales');

-- Natures douane
INSERT INTO nature_recette (id_categorie, code, libelle) VALUES
 (2,'DROIT_DOU','Droit de douane'),
 (2,'TVA_IMPORT','TVA à l''importation'),
 (2,'TAX_PROD_PET','Taxe sur les produits pétroliers'),
 (2,'TVA_PROD_PET','TVA sur les produits pétroliers');

-- Natures non fiscales
INSERT INTO nature_recette (id_categorie, code, libelle) VALUES
 (3,'DIVID','Dividendes'),
 (3,'REDEV_MIN','Redevances minières'),
 (3,'AUTRES_NF','Autres recettes non fiscales');

-- Nature dons
INSERT INTO nature_recette (id_categorie, code, libelle) VALUES
 (4,'DONS_TOTAL','Dons totaux');

-- TVA
INSERT INTO recette (id_nature_recette, id_exercice, montant, commentaire) VALUES
 (4, 1, 1400.20, 'TVA LFR 2024'),
 (4, 2, 1742.20, 'TVA LF 2025');

-- Droits d’accise
INSERT INTO recette (id_nature_recette, id_exercice, montant) VALUES
 (5,1,754.19),(5,2,955.40);

-- Douanes
INSERT INTO recette (id_nature_recette, id_exercice, montant) VALUES
 (7,1,847.50),(7,2,1010.70),
 (8,1,1768.30),(8,2,2148.30);

-- Recettes non fiscales
INSERT INTO recette (id_nature_recette, id_exercice, montant) VALUES
 (9,1,89.5),(9,2,120.2),
 (10,1,84.9),(10,2,331.2);

-- Dons globaux
INSERT INTO recette (id_nature_recette, id_exercice, montant, commentaire) VALUES
 ((SELECT id_nature_recette FROM nature_recette WHERE code='DONS_TOTAL'), 1, 1086.30, 'Dons 2024'),
 ((SELECT id_nature_recette FROM nature_recette WHERE code='DONS_TOTAL'), 2, 2476.60, 'Dons 2025');

-- Ventilation des dons
INSERT INTO detail_don (id_recette, type_don, montant)
VALUES
 ((SELECT r.id_recette FROM recette r JOIN nature_recette n ON r.id_nature_recette=n.id_nature_recette WHERE n.code='DONS_TOTAL' AND id_exercice=2), 'Don courant', 31.0),
 ((SELECT r.id_recette FROM recette r JOIN nature_recette n ON r.id_nature_recette=n.id_nature_recette WHERE n.code='DONS_TOTAL' AND id_exercice=2), 'Don en capital', 2445.6);


-- Insert départements
INSERT INTO departements (nom) VALUES
('Finance'),
('Ressources Humaines'),
('Informatique'),
('Marketing');

-- Insert employés
INSERT INTO employes (nom, email, id_departement) VALUES
('John Doe', 'john.doe@example.com', 1),
('Jane Smith', 'jane.smith@example.com', 2),
('Alice Martin', 'alice.martin@example.com', 3),
('Bob Johnson', 'bob.johnson@example.com', 3),
('Claire Dupont', 'claire.dupont@example.com', 4);

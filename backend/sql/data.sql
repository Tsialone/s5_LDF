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

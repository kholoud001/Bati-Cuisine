--Client Table
 CREATE TABLE IF NOT EXISTS clients(
     id SERIAL PRIMARY KEY ,
     nom VARCHAR(255) NOT NULL,
     telephone VARCHAR(255) NOT NULL,
     estProfessionel BOOLEAN
 );

--Etat Projet ENUM
CREATE TYPE etat_projet_enum AS ENUM ('EN_COURS','TERMINE','ANNULE');

--Projet Table
CREATE TABLE IF NOT EXISTS projets(
    id SERIAL PRIMARY KEY,
    nomProjet VARCHAR(255) NOT NULL,
    margeBeneficiaire DOUBLE PRECISION,
    coutTotal DOUBLE PRECISION,
    etatProjet etat_projet_enum NOT NULL,
    client_id INT NOT NULL REFERENCES clients(id) ON DELETE CASCADE
);

-- Devis Table
CREATE TABLE IF NOT EXISTS devis(
    id SERIAL PRIMARY KEY,
    montantEstime DOUBLE PRECISION,
    dateEmission DATE,
    dateValidite DATE,
    accepte BOOLEAN,
    projet_id INT NOT NULL REFERENCES projets(id) ON DELETE CASCADE
);


--Composant Table
CREATE TABLE IF NOT EXISTS composants(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    tauxTVA DOUBLE PRECISION,
    typeComposant VARCHAR(255),
    projet_id INT NOT NULL REFERENCES projets(id) ON DELETE CASCADE
);

-- Main d'oeuvre Table
CREATE TABLE IF NOT EXISTS main_oeuvres(
     taux_horaire DOUBLE PRECISION ,
     heures_travail DOUBLE PRECISION,
     productivite_ouvrier DOUBLE PRECISION
) INHERITS (composants);

--Materiel Table
CREATE TABLE IF NOT EXISTS matériaux(
     cout_unitaire DOUBLE PRECISION,
     cout_transport DOUBLE PRECISION,
     coefficient_qualite DOUBLE PRECISION,
     quantite DOUBLE PRECISION
)INHERITS (composants);



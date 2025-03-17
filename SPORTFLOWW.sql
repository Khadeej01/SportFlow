create database SportFlow ;
use SportFlow ;

CREATE TABLE membre (
    id_membre INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    sport_pratique VARCHAR(50) NOT NULL
);

CREATE TABLE entraineur (
    id_entraineur INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    specialite VARCHAR(50) NOT NULL
);

CREATE TABLE admin (
    id_admin INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'admin'
);

CREATE TABLE seance (
    id_seance INT PRIMARY KEY AUTO_INCREMENT,
    id_membre INT NOT NULL,
    id_entraineur INT NOT NULL,
    date_heure DATETIME NOT NULL,
    FOREIGN KEY (id_membre) REFERENCES membre(id_membre) ON DELETE CASCADE,
    FOREIGN KEY (id_entraineur) REFERENCES entraineur(id_entraineur) ON DELETE CASCADE
);

SHOW tables ;
SELECT * FROM membre ;
SELECT * FROM admin ;
SELECT * FROM entraineur ;
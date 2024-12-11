-- Datenbank erstellen
CREATE DATABASE OnlineBanking;

-- Datenbank auswählen
USE OnlineBanking;

-- Tabelle für Benutzer
CREATE TABLE Benutzer (
    benutzer_id INT AUTO_INCREMENT PRIMARY KEY,
    benutzername VARCHAR(50) NOT NULL UNIQUE,
    passwort VARCHAR(255) NOT NULL, -- Passwörter sollten gehasht gespeichert werden
    email VARCHAR(100) NOT NULL UNIQUE,
    erstellt_am TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabelle für Konten
CREATE TABLE Konten (
    konto_id INT AUTO_INCREMENT PRIMARY KEY,
    benutzer_id INT NOT NULL,
    kontonummer VARCHAR(20) NOT NULL UNIQUE,
    saldo DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    kontotyp ENUM('Girokonto', 'Sparkonto') NOT NULL,
    erstellt_am TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (benutzer_id) REFERENCES Benutzer(benutzer_id) ON DELETE CASCADE
);

-- Tabelle für Transaktionen
CREATE TABLE Transaktionen (
    transaktion_id INT AUTO_INCREMENT PRIMARY KEY,
    von_konto_id INT NOT NULL, -- Konto, von dem das Geld kommt
    zu_konto_id INT NOT NULL,   -- Konto, zu dem das Geld geht
    transaktionstyp ENUM('Einzahlung', 'Abhebung', 'Überweisung') NOT NULL,
    betrag DECIMAL(10, 2) NOT NULL,
    transaktionsdatum TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (von_konto_id) REFERENCES Konten(konto_id) ON DELETE CASCADE,
    FOREIGN KEY (zu_konto_id) REFERENCES Konten(konto_id) ON DELETE CASCADE
);
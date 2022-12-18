CREATE TABLE utente(
idUtente int PRIMARY KEY AUTO_INCREMENT,
nome varchar(30) NOT NULL,
cognome varchar(30) NOT NULL,
sesso varchar(7) NOT NULL,
dataNascita date NOT NULL,
fotoProfilo varchar(80),
stato varchar(7),
FKcredenziale int,
FOREIGN KEY(FKcredenziale) REFERENCES credenziale(idCredenziale) 
);
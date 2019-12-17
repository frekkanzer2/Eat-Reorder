drop database if exists eatreorder;
create database eatreorder;
use eatreorder;

create table UtenteRegistrato(
email varchar(100) primary key,
password varchar(20) not null,
tipologia varchar(10) not null,
is_banned boolean not null
);

create table Cliente(
nome varchar(20) not null,
cognome varchar(20) not null,
email varchar(100), foreign key (email) references UtenteRegistrato(email)
on update cascade 
on delete cascade,
primary key(email, nome)
);

create table Azienda(
nome varchar(20) not null,
via varchar(20) not null,
numero_civico decimal(3) not null,
citta varchar(15) not null,
provincia character(2) not null,
partita_iva character(11) not null,
telefono varchar(10) not null,
orario_apertura time not null,
orario_chiusura time not null,
email varchar(100), foreign key (email) references UtenteRegistrato(email) 
on update cascade 
on delete cascade,
primary key(email, nome)
);

create table Fattorino(
nome varchar(20) not null, 
cognome varchar(20) not null,
telefono varchar(10) not null,
citta_consegna varchar(20) not null,
provincia character(2) not null,
orario_inizio time not null,
orario_fine time not null,
email varchar(100), foreign key (email) references UtenteRegistrato(email) 
on update cascade 
on delete cascade,
primary key(email,nome)
);

create table Prodotto( 
codice int AUTO_INCREMENT,
nome varchar(25) not null,
descrizione varchar(250) not null,
prezzo decimal not null,
path_immagine varchar(250) not null,
azienda varchar(20),
email varchar(100), 
foreign key (email,azienda) references Azienda(email,nome)
on update cascade 
on delete cascade,
primary key(codice)
);

create table Ordine(
codice int AUTO_INCREMENT,
indirizzo_consegna varchar(30) not null,
numero_carta character(16) not null,
prezzo_totale decimal not null,
note varchar(150),
stato varchar(20) not null,
acquirente varchar(20), 
email varchar(100), 
foreign key (email, acquirente) references Cliente(email, nome)
on update cascade 
on delete cascade,
azienda varchar(20), foreign key (email, azienda) references Azienda(email, nome)
on update cascade 
on delete set null,
fattorino varchar(20), foreign key (email, fattorino) references Fattorino(email, nome)
on update cascade 
on delete cascade,
primary key(codice)
);

create table GiorniLavorativi(
giorno varchar(10) not null,
email varchar(100), foreign key (email) references UtenteRegistrato(email)
on update cascade 
on delete cascade,
primary key(email,giorno)
);

create table Moderatore(
id int not null,
email varchar(100), foreign key (email) references UtenteRegistrato(email)
on update cascade 
on delete cascade,
primary key(email,id)
);

create table ProdottoOrdine(
quantita decimal(2) not null,
prodotto int, foreign key (prodotto) references Prodotto(codice)
on update cascade 
on delete cascade,
ordine int, foreign key (prodotto) references Ordine(codice)
on update cascade 
on delete cascade,
primary key(prodotto,ordine)
);





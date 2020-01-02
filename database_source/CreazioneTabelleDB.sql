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
email_acquirente varchar(100), 
foreign key (email_acquirente, acquirente) references Cliente(email, nome)
on update cascade 
on delete cascade,
azienda varchar(20),
email_azienda varchar(100), 
foreign key (email_azienda, azienda) references Azienda(email, nome)
on update cascade 
on delete set null,
fattorino varchar(20), 
email_fattorino varchar(100),
foreign key (email_fattorino, fattorino) references Fattorino(email, nome)
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


/* Query GestoreOrdineDAOImpl */

#creaOrdine(order : Ordine, user : AccountUtente, cart : Carrello)
select Fattorino.email, Fattorino.nome, GiorniLavorativi.giorno, Fattorino.orario_inizio, Fattorino.orario_fine
from Fattorino, GiorniLavorativi
where Fattorino.citta_consegna=? and GiorniLavorativi.giorno=? and Fattorino.orario_inizio<? and Fattorino.orario_fine>?;

insert into Ordine (indirizzo_consegna, numero_carta, prezzo_totale, note, stato, acquirente, email_acquirente, azienda, email_azienda, fattorino, email_fattorino) values 
(?,?,?,?,?,?,?,?,?,?,?);

insert into ProdottoOrdine (quantita, prodotto, ordine) values
(?, ?, ?);

#controlloEsistenzaOrdine(orderCode: Long)
select *
from Ordine 
where Ordine.codice=?;  #codice dell'ordine

#dammiOrdiniPreparazione(azienda : AccountAzienda)
select Ordine.codice, Ordine.stato
from Ordine
where Ordine.stato=? and Ordine.email_azienda=?;  #preparazione

#dammiOrdine(codice : Long)
select *
from Ordine
where Ordine.codice=?;

#dammiConsegne(fattorino : AccountFattorino)
select Ordine.codice, Fattorino.email, Fattorino.nome
from Fattorino, Ordine
where Fattorino.email=?;

#ordineSetRitirato(orderCode : Long)
update Ordine
set Ordine.stato=?;  #ritirato

#ordineSetConsegnato(orderCode : Long)
update Ordine
set Ordine.stato=?;  #consegnato



/* Query GestoreUtenteDaoImpl */

#controlloEsistenzaMail(email: String)
select * 
from UtenteRegistrato
where UtenteRegistrato.email=?;

#registrazioneCliente(cliente: AccountCliente)
create table Cliente(
nome varchar(20) not null,
cognome varchar(20) not null,
email varchar(100), foreign key (email) references UtenteRegistrato(email)
on update cascade 
on delete cascade,
primary key(email, nome)
);

insert into Cliente(nome, cognome, email) values (?,?,?);

insert into Cliente(nome, cognome, email) values ("b","c","a");
insert into UtenteRegistrato(email, password, tipologia, is_banned) values ("a","a","aaa", true);

update Cliente 
set Cliente.nome="ddd", Cliente.cognome="ddd", UtenteRegistrato.password="ddd"
from Cliente, UtenteRegistrato
where Cliente.email=UtenteRegistrato.email;

select * 
from UtenteRegistrato;

#registrazioneAzienda(azienda: AccountAzienda)
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

insert into Azienda(nome, via, numero_civico, citta, provincia, partita_iva, telefono, orario_apertura, orario_chiusura, email) values (?,?,?,?,?,?,?,?,?,?);

#registrazioneFattorino(fatt: AccountFattorino)
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

insert into Fattorino(nome, cognome, telefono, citta_consegna, orario_inizio, orario_fine, email) values (?,?,?,?,?,?,?);

#controllaBan(email: String)
select Azienda.email
from Azienda
where Azienda.email=?;

#controllaEsistenzaAccount(user: String, pass: String)
select * 
from UtenteRegistrato
where UtenteRegistrato.email=? and UtenteRegistrato.password=?;

#dammiUtente(email: String)
select *
from UtenteRegistrato
where UtenteRegistrato.email=?;

#aggiornaCliente(cliente: AccountCliente)		#DaRivedere
update Cliente, UtenteRegistrato 
set Cliente.nome=?, Cliente.cognome=?, UtenteRegistrato.password=?;

#aggiornaAzienda(azienda: AccountAzienda)     	#DaRivedere
update Azienda, UtenteRegistrato
set Azienda.nome=?, Azienda.via=?, Azienda.numero_civico=?, Azienda.citta=?, Azienda.provincia=?, Azienda.partita_iva=?, Azienda.telefono=?, Azienda.orario_apertura=?, Azienda.orario_chiusura=?, UtenteRegistrato.password=?; 

#aggiornaFattorino(fatt: AccountFattorino)		#DaRivedere
update Fattorino, UtenteRegistrato
set Fattorino.nome=?, Fattorino.cognome=?, Fattorino.telefono=?, Fattorino.citta_consegna=?, Fattorino.provincia=?, Fattorino.orario_inizio=?, Fattorino.orario_fine=?, UtenteRegistrato.password=?;

#dammiAziendaConOrdine(ordine: Ordine)
select distinct Ordine.email_azienda, Ordine.azienda
from Ordine
where Ordine.email_azienda=?; 

#banAzienda(azienda: AccountAzienda)
select Azienda.email
from Azienda
where Azienda.email=?;

#dammiListaAziende(citta: String)
select *
from Azienda
where Azienda.citta=?;

#aggiungiAlListino(azienda: AccountAzienda, prod : Prodotto)
select Prodotto.codice
from Prodotto
where Prodotto.codice=? and Prodotto.email=?;

#aggiornaProdotto(azienda: AccountAzienda, prod : Prodotto)
update Prodotto
set Prodotto.nome=?, Prodotto.descrizione=?, Prodotto.prezzo=?, Prodotto.path_immagine=?
where Prodotto.email=?;

#rimuoviProdotto(azienda: AccountAzienda, prod : Prodotto)
delete from Prodotto
where Prodotto.codice=? and Prodotto.email=?;   
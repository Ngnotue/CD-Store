INSERT INTO CLIENTE(user_id,pwd,cod_fiscale,nome,cognome,citta_residenza,telefono,cellulare)
	VALUES 	('gbellorio','gbellorio','BLLGNN95H18L781X','Giovanni','Bellorio','Bosco Chiesanuova','0457050306','3207522170'),
		('aslemer','aslemer','XXXXXXXXXXXXXXXX','Andrea','Slemer','Verona','XXXXXXXXXX','XXXXXXXXXX'),
		('dimola','dimola','XXXXXXXXXXXXXXXY','Davide','Imola','Verona','XXXXXXXXXX','XXXXXXXXXX');

INSERT INTO ORDINE(id_cliente,prezzo,data_acquisto,ora_acquisto,indirizzo_ip,modalita_pagamento,modalita_consegna)
	VALUES 	(1,10.20,'2016-05-28','16:10','155.16.1.1','CARTA','POSTA'),
		(1,50.00,'2017-02-10','10:00','155.16.1.10','CARTA','CORRI');

INSERT INTO CARRELLO(id_cliente)
	VALUES 	(1);

INSERT INTO ARTISTA(nome)
	VALUES 	('Ligabue'),('Zucchero'),('Vasco Rossi'),('Laura Pausini');

INSERT INTO GENERE(nome)
	VALUES 	('Pop'),('Rock'),('Blues'),('Hard-Rock');

INSERT INTO MUSICISTA(nome_arte,id_genere,anno_nascita)
	VALUES 	('Lele Melotti',1,'1963'),
		('Luca Colombo',2,'1980'),
		('Maurizio Dei Lazzaretti',2,'1960'),
		('Alberto Patuzzo',3,'1986'),
		('Enrico Bentivoglio',3,'1962');

INSERT INTO ARTISTA_MUSICISTA(id_artista,id_musicista)
	VALUES 	(2,1),(1,5);

INSERT INTO DISCO(titolo,prezzo,data_sito,id_artista,descrizione,quantita)
	VALUES 	('Made in Italy',15.00,'2017-01-01',1,'Album Luciano Ligabue 14 Canzoni',20);

INSERT INTO TITOLO(id_disco,nome_canzone)
	VALUES 	(1,'La vita facile'),
		(1,'MI chiamano tutti Riko'),
		(1,'Vittime e complici'),
		(1,'Meno male'),
		(1,'G come giungla');

INSERT INTO DISCO_GENERE(id_disco,id_genere)
	VALUES 	(1,2);

INSERT INTO CARRELLO_DISCO(id_carrello,id_disco)
	VALUES 	(1,1);

INSERT INTO ORDINE_DISCO(id_ordine,id_disco)
	VALUES 	(1,1),(2,1);

INSERT INTO STRUMENTO(nome)
	VALUES 	('Batteria'),('Chitarra'),('Sax');

INSERT INTO MUSICISTA_STRUMENTO(id_musicista,id_strumento)
	VALUES 	(1,1),(2,2),(3,1),(4,1),(5,3);

INSERT INTO SUONA(id_disco,id_musicista,id_strumento)
	VALUES 	(1,1,1),(1,2,2);
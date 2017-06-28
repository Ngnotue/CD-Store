INSERT INTO CLIENTE(user_id,pwd,cod_fiscale,nome,cognome,citta_residenza,telefono,cellulare)
	VALUES 	('gbellorio','1eea6419033bb33d941108e6920aec2','BLLGNN95H18L781X','Giovanni','Bellorio','Bosco Chiesanuova','0457050306','3207522170'),
		('aslemer','b7559006ee35b381b4d109c2f54eff44','SLMNDR95H14L781L','Andrea','Slemer','Quinto di Valpantena','045724531','3490624424'),
		('dimola','d0d8458ed4631d96e7f607e1b57c428a','MLIDVD95H29L781K','Davide','Imola','Verona','045576079','3405169342');

INSERT INTO ARTISTA(nome)
	VALUES 	('Ligabue'),('Ed Sheeran'),('Linkin Park'),('System Of A Down'),('AC/DC'),('Guns N'' Roses'),('Coldplay');

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
	VALUES 	('Made in Italy',15.00,'2017-01-01',1,'14 brani, 49 min',20),
					('LIVING THINGS',14.38,'2017-01-01',3,'12 brani, 37 min',5),
					('Toxicity',7.50,'2017-01-01',4,'15 brani, 44 min',20),
					('A Head Full Of Dreams',8.99,'2017-01-01',7,'11 brani, 45 min',15),
					('Appetite For Destruction',9.18,'2017-01-01',6,'15 brani, 44 min',22);

INSERT INTO TITOLO(id_disco,nome_canzone)
	VALUES 	(1,'La vita facile'),
		(1,'MI chiamano tutti Riko'),
		(1,'Vittime e complici'),
		(1,'Meno male'),
		(1,'G come giungla');

INSERT INTO DISCO_GENERE(id_disco,id_genere)
	VALUES 	(1,2),(2,4),(3,4),(4,1),(5,2);

INSERT INTO STRUMENTO(nome)
	VALUES 	('Batteria'),('Chitarra'),('Sax');

INSERT INTO MUSICISTA_STRUMENTO(id_musicista,id_strumento)
	VALUES 	(1,1),(2,2),(3,1),(4,1),(5,3);

INSERT INTO SUONA(id_disco,id_musicista,id_strumento)
	VALUES 	(1,1,1),(1,2,2);

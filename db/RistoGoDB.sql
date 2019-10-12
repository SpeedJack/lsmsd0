CREATE DATABASE  IF NOT EXISTS `ristogo`;
USE `ristogo`;

DROP TABLE IF EXISTS `utente`;

CREATE TABLE `utente` (
  `IdUtente` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `tipo` varchar(32) NOT NULL,
  PRIMARY KEY (`IdUtente`,`nome` ),
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


INSERT INTO utente VALUES ('1', 'simone', 'simone', 'cliente');
INSERT INTO utente VALUES ('2', 'luca', 'luca', 'ristoratore');
INSERT INTO utente VALUES ('3', 'gino', 'gino', 'cliente');
INSERT INTO utente VALUES ('4', 'carlotta', 'carlotta', 'ristoratore');
INSERT INTO utente VALUES ('5', 'gianluca', 'gianluca', 'ristoratore');
INSERT INTO utente VALUES ('6', 'yin', 'yin', 'ristoratore');
INSERT INTO utente VALUES ('7', 'mark', 'mark', 'ristoratore');
INSERT INTO utente VALUES ('8', 'chicarito', 'chicarito', 'ristoratore');
INSERT INTO utente VALUES ('9', 'gianni', 'gianni', 'ristoratore');
INSERT INTO utente VALUES ('10', 'gabriele97', 'gabriele97', 'cliente');

DROP TABLE IF EXISTS `ristorante`;

CREATE TABLE `ristorante` (
  `IdRisto` int(11) NOT NULL,
  `IdUtente` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `genere` varchar(32) NOT NULL,
  `costo` int(11) NOT NULL,
  `citta` varchar(32) NOT NULL,
  `via` varchar(32) NOT NULL,
  `descrizione` varchar(2000) NOT NULL,
  `coperti` int(11) NOT NULL,
  `oraApertura` varchar(255) NOT NULL,
  `oraChiusura` varchar(255) NOT NULL,
  PRIMARY KEY (`IdRisto`, `IdUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


INSERT INTO `ristorante` VALUES (1,4,'La Baita del Pirata','ristorante',4,'Donoratico','Del Cipresso, 37','La Baita del Pirata Ã¨ il sogno di due fratelli: Alberto e Carlotta Olmi, che nel 1970 decidono di investire nella bellezza e nelle potenzialitÃ  della Costa degli Etruschi, luogo dallo charme unico ed autentico, cullato tra la dolce campagna di Bolgheri e le onde del Tirreno. Da allora la struttura sulla spiaggia Ã¨ cresciuta insieme alla famiglia Olmi, senza perdere il fascino delle origini, dotandosi di una splendida terrazza panoramica, fruscianti ombrelloni di rafia, accoglienti tende e diventando uno dei piÃ¹ rinomati ristoranti della Costa, segnalato dalle piÃ¹ prestigiose Guide Gourmand. ',15,'20:00','23:00'),
(2,2,'Pizzeria da Luca','pizzeria',2,'Pisa','Milano, 14','La vera pizza napoletana preparata secondo la tradizione della ricetta con vera Mozzarella di Bufala Campana DOP. Troverete unâ€™atmosfera allegra e la splendida simpatia dei gestori e di tutto lo staff. Lâ€™ambiente Ã¨ familiare ed accogliente e ripropone alcuni dettagli della cittÃ  partenopea.\r\nI nostri ingredienti sono importati direttamente dalla Campania e sono selezionati con grande attenzione: la mozzarella di bufala campana che dona quel sapore fresco alle pizze d.o.c, la farina di grano tenero tipo Â«00Â», il lievito di birra, i pomodori pelati e freschi, lâ€™olio dâ€™oliva extravergine e il basilico. Tutto questo viene cotto nel forno a legna con Legna di Cerro, Quercia, Leccio.',30,'19:30','23:00'),
(3,5,'Osteria Del Cason','osteria',3,'Cascina','Torino, 78','I valori che hanno dato vita alla nostra attivitÃ  sono la passione per il nostro lavoro, la forte cultura aziendale di unâ€™impresa familiare e la costante ricerca della soddisfazione del cliente. Troverete pietanze toscane e vini di produzione propria serviti tra sedie colorate e un ambiente familiare.',10,'20:00','23:30'),
(4,6,'La Felicita','cinese',1,'Pontedera','Tosco Romagnola Est, 12','MENU\r\nANTIPASTI\r\nSpring Rolls  2,5 â‚¬,\r\nEdame 3 â‚¬,\r\nNuvolette di drago 2 â‚¬,\r\nZuppa di Wonton 4 â‚¬,\r\nZuppa di granchio ed asparagi 4 â‚¬,\r\nZuppa di manzo con verdure4 â‚¬,\r\nPRIMI\r\nSpaghetti saltati con verdure 5 â‚¬,\r\nSpaghetti ai frutti di mare 6 â‚¬,\r\nSpaghetti saltati con frutti di mare e verdure alla piastra 6,5 â‚¬,\r\nRiso alle verdure 5 â‚¬,\r\nRiso Thai 7 â‚¬,\r\nRiso nero con carne 8 â‚¬,\r\nSECONDI\r\nbocconcini di pollo con anacardi 6,5 â‚¬,\r\nPollo al curry 6 â‚¬,\r\nPancetta roll 8 â‚¬,\r\nManzo con verdure in brodo 7 â‚¬,\r\nManzo saltato con peperoncino 7 â‚¬,\r\nGamberi fritti 8 â‚¬,\r\nMaiale agrodolce al piccante 8 â‚¬,\r\nCalamari piccanti in padella 7 â‚¬,\r\nGamberi con cipollini, sale e pepe 7 â‚¬,\r\nZuppa di pesce e verdura 5 â‚¬,\r\nTofu giapponese saltato di verdure 6 â‚¬,\r\nBranzino grigliato con sale, pepe e cumino 10 â‚¬,\r\nDESSERT\r\nDolcetti dâ€™oriente 4 â‚¬',40,'18:30','00:00'),
(5,7,'HomeHam','steackhouse',3,'Pisa','Diotisalvi, 1','Tagli di carne selezionati e grigliati al momento, tante proposte sfiziose e di qualitÃ , promozioni quotidiane e offerte dedicate: HomeHam Ã¨ questo ma anche tanto altro. Abbiamo scelto di essere un ristorante in cui la qualitÃ  non si assaggia solamente al tavolo ma anche nei servizi, nellâ€™atmosfera e nelle facilities tecnologiche che ti offriamo, per questo siamo il ristorante che non câ€™era.',35,'20:30','02:00'),
(6,8,'AL Messicano','messicano',3,'Pisa','Giacomo Leopardi, 3','Cocktail esplosivo di colori e piacere per gli occhi ed il palato (fiore allâ€™occhiello del locale sono gli splendidi wc maiolicati), la nuova sede di via Giacomo Leopardi 3 aspetta tutti gli appassionati di cucina messicana: 30 anni di storia ed esperienza sono solo un ottimo inizio per stupire ancora e ripartire.',12,'20:00','00:00'),
(7,9,'Zen Giapponese','giapponese',5,'Cascina','Zizzo, 66','Da oltre 15 anni Zen a Cascina Ã¨ il riferimento per tutti coloro che desiderano assaporare lâ€™autentica cucina Giapponese in unâ€™atmosfera unica. La lunga esperienza degli chef, le materie prime freschissime e selezionate dal meglio dei mercati locali, per un assortimento di proposte culinarie che hanno decretato il successo di Zen.',20,'19:00','00:00');


DROP TABLE IF EXISTS `prenotazione`;

CREATE TABLE `prenotazione` (
  `nomeCliente` varchar(32) NOT NULL,
  `IdRisto` int(11) NOT NULL,
  `data` date NOT NULL,
  `ora` varchar(32) NOT NULL,
  `persone` int(11) NOT NULL,
  PRIMARY KEY (`nomeCliente`, `IdRisto`, `data`, `ora`, `persone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


INSERT INTO prenotazione VALUES ('gabriele97', '2', '2018-06-18', '22:00', '5');
INSERT INTO prenotazione VALUES ('gino', '2', '2018-06-26', '21:00', '6');
INSERT INTO prenotazione VALUES ('simone', '1', '2018-06-19', '20:45', '2');
INSERT INTO prenotazione VALUES ('simone', '2', '2018-06-26', '20:00', '3');
INSERT INTO prenotazione VALUES ('simone', '5', '2018-06-16', '22:00', '6');

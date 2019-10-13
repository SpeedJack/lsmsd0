DELIMITER $$

CREATE TRIGGER controllo_posti BEFORE INSERT ON ristogo.prenotazione
FOR EACH ROW
BEGIN
SET @posti_prenotati = (SELECT SUM(persone)
	FROM prenotazione
	WHERE idRisto = new.idRisto
    );
SET @posti_totali = (SELECT coperti FROM ristorante WHERE idRisto = new.idRisto);

IF @posti_prenotati + new.persone > @posti_totali THEN
	signal sqlstate '45000' set message_text = 'Non ci sono coperti a sufficienza per questa prenotazione';

ELSE IF new.data < date(now()) OR (new.data >= date(now()) and hour(now) > hour(new.ora))THEN
	signal sqlstate '45000' set message_text = 'Non è possibile prenotare per date  o orari passati';

end $$
delimiter ;


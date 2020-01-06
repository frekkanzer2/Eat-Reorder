package model;
/**
 * Classe con metodi statici che consente la verifica dei dati di varie entitא
 * @author Rosario
 *
 */

public class CheckFormato {
	
	public static boolean formatoRegistrazioneCliente(String email,String password,String nome,String cognome) {
		boolean in_email=email.matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean in_password=password.matches("[a-zA-Z0-9]{7,20}");
		boolean in_nome=nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_cognome=cognome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		if (in_email == true && in_password == true && in_nome == true && in_cognome == true) 
			return true;
		return false;
	}
	public static boolean formatoRegistrazioneAzienda(String email,String password,String nome,String via,int civico,String citta,String provincia,String telefono) {
		boolean in_email=email.matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean in_password=password.matches("[a-zA-Z0-9]{7,20}");
		boolean in_nome=nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_via=via.matches("[a-zA-Z ‘אטלעש]{3,27}");
		boolean in_civico=(civico>0&&civico<1000)?true:false;
		boolean in_citta=citta.matches("[a-zA-Z ‘אטלעש]{4,15}");
		boolean in_provincia=provincia.matches("[a-zA-Z]{2}");
		boolean in_telefono=telefono.matches("[0-9]{9,10}");
		if(in_email==true&&in_password==true&&in_nome==true&&in_via==true&&in_civico==true&&in_citta==true&&in_provincia==true&&in_telefono==true)
			return true;
		return false;
	}
	public static boolean formatoRegistrazioneFattorino(String email,String password,String nome,String cognome,String telefono,String citta,String provincia) {
		boolean in_email=email.matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean in_password=password.matches("[a-zA-Z0-9]{7,20}");
		boolean in_nome=nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_cognome=cognome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_telefono=telefono.matches("[0-9]{9,10}");
		boolean in_citta=citta.matches("[a-zA-Z ‘אטלעש]{4,15}");
		boolean in_provincia=provincia.matches("[a-zA-Z]{2}");
		if(in_email==true&&in_password==true&&in_nome==true&&in_cognome==true&&in_telefono==true&&in_citta==true&&in_provincia==true)
			return true;
		return false;
	}
	public static boolean formatoModificaCliente(String nome,String cognome,String password) {
		boolean in_nome=nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_cognome=cognome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_password=password.matches("[a-zA-Z0-9]{7,20}");
		if(in_nome==true&&in_cognome==true&&in_password==true)
			return true;
		return false;
	}
	public static boolean formatoModificaAzienda(String nome,String via,int civico,String citta,String provincia,String telefono,String password) {
		boolean in_nome=nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_via=via.matches("[a-zA-Z ‘אטלעש]{3,27}");
		boolean in_civico=(civico>0&&civico<1000)?true:false;
		boolean in_citta=citta.matches("[a-zA-Z ‘אטלעש]{4,15}");
		boolean in_provincia=provincia.matches("[a-zA-Z]{2}");
		boolean in_telefono=telefono.matches("[0-9]{9,10}");
		boolean in_password=password.matches("[a-zA-Z0-9]{7,20}");
		if(in_nome==true&&in_via==true&&in_civico==true&&in_citta==true&&in_provincia==true&&in_telefono==true&&in_password==true)
			return true;
		return false;
	}
	public static boolean formatoModificaFattorino(String nome,String cognome,String telefono,String citta,String provincia,String password) {
		boolean in_nome=nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_cognome=cognome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_telefono=telefono.matches("[0-9]{9,10}");
		boolean in_citta=citta.matches("[a-zA-Z ‘אטלעש]{4,15}");
		boolean in_provincia=provincia.matches("[a-zA-Z]{2}");
		boolean in_password=password.matches("[a-zA-Z0-9]{7,20}");
		if(in_nome==true&&in_cognome==true&&in_telefono==true&&in_citta==true&&in_provincia==true&&in_password==true)
			return true;
		return false;
	}
	public static boolean formatoSegnalazione(int ordine,String descrizione) {
		boolean in_ordine=(ordine>0)?true:false;
		boolean in_descrizione=descrizione.matches("[a-zA-Z0-9\\s.’אטלעש]{10,250}");
		if(in_ordine==true&&in_descrizione==true)
			return true;
		return false;
	}
	public static boolean formatoGestioneSegnalazione(int ordine) {
		boolean in_ordine=(ordine>0)?true:false;
		if(in_ordine==true)
			return true;
		return false;
	}
	public static boolean formatoModificaQuantita(int quantita) {
		boolean in_quantita=(quantita>0&&quantita<51)?true:false;
		if(in_quantita==true)
			return true;
		return false;
	}
	//non funziona
	public static boolean formatoOrdinazione(String indirizzo,String carta,String note) {
		boolean in_indirizzo=indirizzo.matches("[a-zA-Z‘אטלעש]{1,27},[0-9]{1,3}");
		boolean in_carta=carta.matches("[0-9]{16}");
		boolean in_note=note.matches("[a-zA-Z0-9\\s.,’אטלעש]{0,150}}");
		if(in_indirizzo==true&&in_carta==true&&in_note==true)
			return true;
		return false;
	}
	//non funziona
	public static boolean formatoInserisciProdotto(String prodotto,String descrizione,float prezzo) {
		boolean in_prodotto=prodotto.matches("[a-zA-Z ‘אטלעש]{1,25}");
		boolean in_descrizione=prodotto.matches("[a-zA-Z0-9\\s.‘אטלעש]{10,250}");
		boolean in_prezzo=(prezzo>=0)?true:false;
		if(in_prodotto==true&&in_descrizione==true&&in_prezzo==true)
			return true;
		return false;
	}
	//non funziona
	public static boolean formatoModificaProdotto(String prodotto,String descrizione,float prezzo) {
		boolean in_prodotto=prodotto.matches("[a-zA-Z ‘אטלעש]{1,25}");
		boolean in_descrizione=prodotto.matches("[a-zA-Z0-9\\. ‘אטלעש]{10,250}");
		boolean in_prezzo=(prezzo>=0)?true:false;
		if(in_prodotto==true&&in_descrizione==true&&in_prezzo==true)
			return true;
		return false;
	}
}

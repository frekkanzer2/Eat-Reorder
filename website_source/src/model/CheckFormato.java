package model;

import java.net.URL;

import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Ordine_Bean;

/**
 * Classe con metodi statici che consente la verifica dei dati di varie entità
 * @author Rosario
 *
 */

public class CheckFormato {
	public static boolean checkCliente(AccountCliente_Bean cliente) {
		boolean c_email=cliente.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean c_password=cliente.getPassword().matches("[a-zA-Z0-9]{7,20}");
		boolean c_nome=cliente.getNome().matches("[a-zA-Z ‘àèìòù]{3,40}");
		boolean c_cognome=cliente.getCognome().matches("[a-zA-Z ‘àèìòù]{3,40}");
		if(c_email==true&&c_password==true&&c_nome==true&&c_cognome==true)
			return true;
		return false;
	}
	public static boolean checkAzienda(AccountAzienda_Bean azienda) {
		boolean c_email=azienda.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean c_password=azienda.getPassword().matches("[a-zA-Z0-9]{7,20}");
		boolean c_nome=azienda.getNome().matches("[a-zA-Z ‘àèìòù]{3,40}");
		boolean c_via=azienda.getVia().matches("[a-zA-Z ‘àèìòù]{3,80}");
		boolean c_civico=(azienda.getNumeroCivico()>0&&azienda.getNumeroCivico()<1000)?true:false;
		boolean c_citta=azienda.getCitta().matches("[a-zA-Z ‘àèìòù]{4,45}");
		boolean c_provincia=azienda.getProvincia().matches("[a-zA-Z]{2}");
		boolean c_telefono=azienda.getTelefono().matches("[0-9]{9,10}");
		boolean c_iva=azienda.getPartitaIva().matches("[0-9]{11}");
		if(c_email==true&&c_password==true&&c_nome==true&&c_via==true&&c_civico==true&&c_citta==true&&c_provincia==true&&c_telefono==true&&c_iva==true)
			return true;
		return false;
		
	}
	public static boolean checkFattorino(AccountFattorino_Bean fattorino) {
		boolean c_email=fattorino.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean c_password=fattorino.getPassword().matches("[a-zA-Z0-9]{7,20}");
		boolean c_nome=fattorino.getNome().matches("[a-zA-Z ‘àèìòù]{3,40}}");
		boolean c_cognome=fattorino.getCognome().matches("[a-zA-Z ‘àèìòù]{3,40}");
		boolean c_telefono=fattorino.getTelefono().matches("[0-9]{9,10}");
		boolean c_citta=fattorino.getCittaConsegna().matches("[a-zA-Z ‘àèìòù]{4,45}");
		boolean c_provincia=fattorino.getProvinciaConsegna().matches("[a-zA-Z]{2}");
		if(c_email==true&&c_password==true&&c_nome==true&&c_cognome==true&&c_telefono==true&&c_citta==true&&c_provincia==true)
			return true;
		return false;
	}
	public static boolean checkSegnalazione(int ordine,String descrizione) {
		boolean in_ordine=(ordine>0)?true:false;
		boolean in_descrizione=descrizione.matches(" [a-zA-Z0-9\\. ’àèìòù]{10,250}");
		if(in_ordine==true&&in_descrizione==true)
			return true;
		return false;
	}
	public static boolean checkQuantità(int quantita) {
		boolean in_quantita=(quantita>0&&quantita<51)?true:false;
		if(in_quantita==true)
			return true;
		return false;
	}
		public static boolean checkOrdine(Ordine_Bean ordine) {
			boolean c_indirizzo=ordine.getIndirizzoConsegna().matches("[a-zA-Z ‘àèìòù]{1,80}, [0-9]{1,3}");
			boolean c_carta=ordine.getCodiceCarta().matches("[0-9]{16}");
			boolean c_note=ordine.getNote().matches("[a-zA-Z0-9\\. ,‘àèìòù]{0,150}");
			if(c_indirizzo==true&&c_carta==true&&c_note==true)
				return true;
			return false;
		}
		public static boolean checkProdotto(String prodotto,URL img,String descrizione,double prezzo) {
			boolean c_prodotto=prodotto.matches("[a-zA-Z ‘àèìòù]{1,45}");
			boolean c_descrizione=descrizione.matches("[a-zA-Z0-9\\. ‘àèìòù]{10,250}");
			String s_img=img.toString();
			boolean c_img=s_img.matches("(?:([A-Za-z]+):)?(\\/{0,3})([0-9.\\-A-Za-z]+)(?::(\\d+))?(?:\\/([^?#]*))?(?:\\?([^#]*))?(?:#(.*))?");
			boolean c_prezzo=(prezzo>=0)?true:false;
			
			if(c_prodotto==true&&c_descrizione==true&&c_img==true&&c_prezzo==true)
				return true;
			return false;
		}
	public static boolean checkUtenteRegistrato(AccountUtenteRegistrato_Bean utente){
		boolean c_email=utente.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean c_password=utente.getPassword().matches("[a-zA-Z0-9]{7,20}");
		if(c_email==true&&c_password==true)
			return true;
		return false;
	}
}

package model;

import java.net.URL;

import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Ordine_Bean;

/**
 * Classe con metodi statici che consente la verifica dei dati di varie entitא
 * 
 * @author Rosario
 *
 */
public class CheckFormato {
	public static boolean checkCliente(AccountCliente_Bean cliente) {
		boolean inputEmail = cliente.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean inputPassword = cliente.getPassword().matches("[a-zA-Z0-9]{7,20}");
		boolean inputNome = cliente.getNome().matches("[a-zA-Z 'אטלעש]{3,40}");
		boolean inputCognome = cliente.getCognome().matches("[a-zA-Z 'אטלעש]{3,40}");
		if (inputEmail == true && inputPassword == true && inputNome == true && inputCognome == true)
			return true;
		return false;
	}

	public static boolean checkAzienda(AccountAzienda_Bean azienda) {
		boolean inputEmail = azienda.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean inputPassword = azienda.getPassword().matches("[a-zA-Z0-9]{7,20}");
		boolean inputNome = azienda.getNome().matches("[a-zA-Z 'אטלעש]{3,40}");
		boolean inputVia = azienda.getVia().matches("[a-zA-Z 'אטלעש]{3,80}");
		boolean inputCivico;
		if (azienda.getNumeroCivico() > 0 && azienda.getNumeroCivico() < 1000) {
			inputCivico = true;
		} else
			inputCivico = false;
		boolean inputCitta = azienda.getCitta().matches("[a-zA-Z 'אטלעש]{4,45}");
		boolean inputProvincia = azienda.getProvincia().matches("[a-zA-Z]{2}");
		boolean inputTelefono = azienda.getTelefono().matches("[0-9]{9,10}");
		boolean inputIva = azienda.getPartitaIva().matches("[0-9]{11}");
		if (inputEmail == true && inputPassword == true && inputNome == true && inputVia == true && inputCivico == true
				&& inputCitta == true && inputProvincia == true && inputTelefono == true && inputIva == true)
			return true;
		return false;
	}

	public static boolean checkFattorino(AccountFattorino_Bean fattorino) {
		boolean inputEmail = fattorino.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean inputPassword = fattorino.getPassword().matches("[a-zA-Z0-9]{7,20}");
		boolean inputNome = fattorino.getNome().matches("[a-zA-Z 'אטלעש]{3,40}");
		boolean inputCognome = fattorino.getCognome().matches("[a-zA-Z 'אטלעש]{3,40}");
		boolean inputTelefono = fattorino.getTelefono().matches("[0-9]{9,10}");
		boolean inputCitta = fattorino.getCittaConsegna().matches("[a-zA-Z 'אטלעש]{4,45}");
		boolean inputProvincia = fattorino.getProvinciaConsegna().matches("[a-zA-Z]{2}");
		if (inputEmail == true && inputPassword == true && inputNome == true && inputCognome == true
				&& inputTelefono == true && inputCitta == true && inputProvincia == true)
			return true;
		return false;
	}

	public static boolean checkSegnalazione(Long ordine, String descrizione) {
		boolean inputOrdine = (ordine > 0) ? true : false;
		boolean inputDescrizione = descrizione.matches("[a-zA-Z0-9\\. ,'אטלעש]{10,150}");
		if (inputOrdine == true && inputDescrizione == true)
			return true;
		return false;
	}

	public static boolean checkQuantitא(int quantita) {
		boolean inputQuantita = (quantita > 0 && quantita < 51) ? true : false;
		if (inputQuantita == true)
			return true;
		return false;
	}

	public static boolean checkOrdine(Ordine_Bean ordine) {
		boolean inputIndirizzo = ordine.getIndirizzoConsegna().matches("[a-zA-Z 'אטלעש]{1,80}, [0-9]{1,3}");
		boolean inputCarta = ordine.getCodiceCarta().matches("[0-9]{16}");
		boolean inputNote = ordine.getNote().matches("[a-zA-Z0-9\\. ,'אטלעש]{0,150}");
		if (inputIndirizzo == true && inputCarta == true && inputNote == true)
			return true;
		return false;
	}

	public static boolean checkProdotto(String prodotto, URL img, String descrizione, double prezzo) {
		boolean inputProdotto = prodotto.matches("[a-zA-Z 'אטלעש]{1,45}");
		boolean inputDescrizione = descrizione.matches("[a-zA-Z0-9\\. 'אטלעש]{10,250}");
		String inImg = img.toString();
		boolean inputImg = inImg.matches(
				"(?:([A-Za-z]+):)?(\\/{0,3})([0-9.\\-A-Za-z]+)(?::(\\d+))?(?:\\/([^?#]*))?(?:\\?([^#]*))?(?:#(.*))?");
		boolean inputPrezzo = (prezzo >= 0) ? true : false;

		if (inputProdotto == true && inputDescrizione == true && inputImg == true && inputPrezzo == true)
			return true;
		return false;
	}

	public static boolean checkUtenteRegistrato(AccountUtenteRegistrato_Bean utente) {
		boolean inputEmail = utente.getEmail().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean inputPassword = utente.getPassword().matches("[a-zA-Z0-9]{7,20}");
		if (inputEmail == true && inputPassword == true)
			return true;
		return false;
	}
}

/*Questa classe gestisce il carrello
 * 
 * 
 * 
 * */
package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.bean.AccountAzienda_Bean;
import model.bean.ProdottoCarrello_Bean;
import model.bean.Prodotto_Bean;

public class Carrello {
	
	private AccountAzienda_Bean currentAzienda;
	
	Map<Long, ProdottoCarrello_Bean> prodotti = new HashMap<Long, ProdottoCarrello_Bean>();
	
	public void aggiornaQtaCarrello (Prodotto_Bean prod, int qta) {
		 
		//TODO implementazione del metodo
	}
	
	public void rimuoviProdotto (Prodotto_Bean prod) {
		
		//TODO implementazione del metodo
	}
	
	 
	public void svuota() {
		
		//TODO implementazione metodo
	}
	
	public boolean checkAziendaCarrello(Prodotto_Bean prod) {
		
		//TODO implmentazione metodo
		return false;
	}
	
	public boolean checkInCarrello(Prodotto_Bean prod) {
		
		//TODO implementzione metodo
		return false;
	}
	
	public void aggiungiProdotto(Prodotto_Bean prod) {
		
		//TODO implementazione metodo
	}
	
	
}

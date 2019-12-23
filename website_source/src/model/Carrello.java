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

public class Carrello {
	
	private AccountAzienda currentAzienda;
	
	Map<Long, ProdottoCarrello> prodotti = new HashMap<Long, ProdottoCarrello>();
	
	public void aggiornaQtaCarrello (Prodotto prod, int qta) {
		 
		//TODO implementazione del metodo
	}
	
	public void rimuoviProdotto (Prodotto prod) {
		
		//TODO implementazione del metodo
	}
	
	 
	public void svuota() {
		
		//TODO implementazione metodo
	}
	
	public boolean checkAziendaCarrello(Prodotto prod) {
		
		//TODO implmentazione metodo
		return false;
	}
	
	public boolean checkInCarrello(Prodotto prod) {
		
		//TODO implementzione metodo
		return false;
	}
	
	public void aggiungiProdotto(Prodotto prod) {
		
		//TODO implementazione metodo
	}
	
	
}

/*Questa classe gestisce il carrello
 * 
 * 
 * 
 * */
package model;

import model.bean.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Carrello {

	private AccountAzienda_Bean currentAzienda = null;

	Map<Long, ProdottoQuantita> prodotti = new HashMap<Long, ProdottoQuantita>();

	
	public void aggiornaQtaCarrello(Prodotto_Bean prod, int qta) {

		if (prod != null) {

			if (qta > 0) {

				if (prodotti.containsKey(prod.getCodice())) {

					ProdottoQuantita x = prodotti.get(prod.getCodice());

					x.setQta(qta);
				}
			}
		}
	}
	
	public Collection<ProdottoQuantita> getProdotti(){
		
		return prodotti.values();
	}

	public void rimuoviProdotto(Prodotto_Bean prod) {

		if (prod != null) {

			if (prodotti.containsKey(prod.getCodice())) {

				prodotti.remove(prod.getCodice());

				if (prodotti.isEmpty())

					currentAzienda = null;
			}
		}
	}

	/**
	 * @return the currentAzienda
	 */
	public AccountAzienda_Bean getCurrentAzienda() {
		return currentAzienda;
	}

	/**
	 * @param currentAzienda the currentAzienda to set
	 */
	

	public void svuota() {

		prodotti.clear();
		currentAzienda = null;
	}

	public boolean checkAziendaCarrello(Prodotto_Bean prod) {

		if (prod != null) {
			if (prod.getAzienda().getEmail().equals(currentAzienda.getEmail()))

				return true;

			else
				return false;
		}

		return false;
	}

	public boolean checkInCarrello(Prodotto_Bean prod) {
		if (prod != null) {
			return prodotti.containsKey(prod.getCodice()) ? true : false;
		}
		return false;
	}

	
	public void aggiungiProdotto(Prodotto_Bean prod) {
		if (prod != null) {
			if (!prodotti.isEmpty()) {
				if (prod.getAzienda().getEmail().equals(currentAzienda.getEmail())) {
					ProdottoQuantita x = new ProdottoQuantita();
					x.setProdotto(prod);
					x.setQta(1);
					prodotti.put(prod.getCodice(), x);
				}
			} else {
				currentAzienda = prod.getAzienda();
				ProdottoQuantita x = new ProdottoQuantita();
				x.setProdotto(prod);
				x.setQta(1);
				prodotti.put(prod.getCodice(), x);
			}
		}

	}

}

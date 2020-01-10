package model;

import model.bean.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe che permette la gestione del carrello. Il carrello permette di
 * inserire prodotti di tipo{@link Prodotto_Bean}. Una volta inserito il primo
 * prodotto, l'azienda associata al carrello diventa uguale a quella del
 * prodotto aggiunto per primo. Tutti i prodotti aggiunti successivamente
 * dovranno appartenere alla stessa azienda altrimenti non verranno aggiunti.
 * 
 * @author Rosario Gagliardi
 *
 */
public class Carrello {

	private AccountAzienda_Bean currentAzienda = null;

	private Map<Long, ProdottoQuantita> prodotti = new HashMap<Long, ProdottoQuantita>();

	/**
	 * * Questo metodo consente di aggiornare la quantità di un prodotto nel
	 * carrello. La quantità viene aggiornata solo se il valore è maggiore di zero e
	 * se il prodotto è presente nel carrello
	 * 
	 * @param prod prodotto da aggiornare
	 * @param qta  nuova quantità del prodotto
	 */
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

	/**
	 * * Restituisce una collectionc con i prodotti presenti nel carrello tramite la
	 * classe ProdottoQuantita
	 * 
	 * @return Collection di prodotti con rispettive quantità
	 */
	public Collection<ProdottoQuantita> getProdotti() {

		return prodotti.values();
	}

	/**
	 * Restituisce un ProdottoQuantita associato al codice passato come parametro
	 * 
	 * @param codice del prodotto nel carrello
	 * @return ProdottoQuantita del prodotto con il codice inserito o null se il
	 *         codice non esiste
	 */

	public ProdottoQuantita getProdotto(long codice) {

		return prodotti.get(codice);
	}

	/**
	 * Metodo che restituisce se il carrello è vuoto
	 * @return true se il carrello è vuoto false altrimenti
	 */
	public boolean isEmpty() {
		return prodotti.isEmpty();
	}

	/**
	 * Rimuove un prodotto dal carrello. Il prodotto viene rimosso solo se il
	 * prodotto è presente nel carrello. In caso il prodotto rimosso fosse l'ultimo,
	 * viene settata a null l'azienda associata al carrello
	 * 
	 * @param prod prodotto da rimuovere dal carrello
	 */
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
	 * @return L'attuale azienda associata al carrello
	 */
	public AccountAzienda_Bean getCurrentAzienda() {
		return currentAzienda;
	}

	/**
	 * Consente di svuotare il carrello. Viene settata a null l'azienda associata
	 */
	public void svuota() {

		prodotti.clear();
		currentAzienda = null;
	}

	/**
	 * Consente di verificare se il prodotto passato come parametro è della stessa
	 * azienda associata al carrello
	 * 
	 * @param prod prodotto da verificare
	 * @return true se il prodotto è della stessa azienda associata al carrello,
	 *         false altrimenti
	 */
	public boolean checkAziendaCarrello(Prodotto_Bean prod) {

		if (prod != null) {
			if (this.prodotti.isEmpty())
				return true;
			else if (prod.getAzienda().getEmail().equals(currentAzienda.getEmail()))

				return true;

			else
				return false;
		}

		return false;
	}

	/**
	 * Verifica se un prodotto è già presente nel carrello
	 * 
	 * @param prod prodotto da verificare
	 * @return true se il prodotto è già presente, false altrimenti
	 */
	public boolean checkInCarrello(Prodotto_Bean prod) {
		if (prod != null) {
			return prodotti.containsKey(prod.getCodice()) ? true : false;
		}
		return false;
	}

	/**
	 * Aggiunge un prodotto al carrello. Se il carrello non è vuoto, il prodotto
	 * viene aggiunto solo se l'azienda associata al carrello è la stessa azienda
	 * del prodotto Nel caso il carrello sia vuoto, il prodotto viene aggiunto e
	 * l'azienda associata al carrello diventa l'azienda del prodotto aggiunto.
	 * 
	 * @param prod prodotto da aggiungere al carrello
	 */
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

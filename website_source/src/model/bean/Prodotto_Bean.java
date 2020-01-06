package model.bean;

import java.net.URL;

public class Prodotto_Bean {

	private Long codice;
	private String nome;
	private String descrizione;
	private URL immagine;
	private Float prezzo;
	private AccountAzienda_Bean azienda;

	/**
	 * @return l'azienda associata al prodotto
	 */
	public AccountAzienda_Bean getAzienda() {
		return azienda;
	}

	/**
	 * @param azienda azienda a cui appartiene il prodotto
	 */
	public void setAzienda(AccountAzienda_Bean azienda) {
		this.azienda = azienda;
	}

	/**
	 * Aggiorna i dati dell'istanza con i dati presenti nell'istanza passata come
	 * parametro. Non viene aggiornato il codice dell'istanza esistente e l'azienda di appartenenza.
	 * 
	 * @param a Istanza di prodotto con i dati aggiornati
	 */
	public void modificaDati(Prodotto_Bean a) {
		if (a != null) {
			this.nome = a.getNome();
			this.descrizione = a.getDescrizione();
			this.immagine = a.getImmagine();
			this.prezzo = a.getPrezzo();
		}
	}

	/**
	 * 
	 * @return codice del prodotto
	 */
	public Long getCodice() {
		return codice;
	}

	/**
	 * 
	 * @param codice codice del prodotto
	 */
	public void setCodice(Long codice) {
		this.codice = codice;
	}

	/**
	 * 
	 * @return nome del prodotto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome nome del prodotto	
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return descrizione del prodotto
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * 
	 * @param descrizione descrizione del prodotto
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * 
	 * @return {@link URL} dell'immagine
	 */
	public URL getImmagine() {
		return immagine;
	}

	/**
	 * 
	 * @param immagine URL dell'immagine 
	 */
	public void setImmagine(URL immagine) {
		this.immagine = immagine;
	}

	/**
	 * 
	 * @return prezzo del prodotto
	 */
	public Float getPrezzo() {
		return prezzo;
	}

	/**
	 * 
	 * @param prezzo prezzo del prodotto
	 */
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

}

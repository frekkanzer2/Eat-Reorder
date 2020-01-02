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
	 * @return the azienda
	 */
	public AccountAzienda_Bean getAzienda() {
		return azienda;
	}

	/**
	 * @param azienda the azienda to set
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

	public Long getCodice() {
		return codice;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public URL getImmagine() {
		return immagine;
	}

	public void setImmagine(URL immagine) {
		this.immagine = immagine;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

}

package model.bean;

import java.util.List;

import model.ProdottoQuantita;

public class Ordine_Bean {

	public static final String IN_PREPARAZIONE = "In preparazione";
	public static final String RITIRATO = "Ritirato";
	public static final String CONSEGNATO = "Consegnato";

	private AccountAzienda_Bean azienda;
	private AccountFattorino_Bean fattorino;
	private AccountCliente_Bean cliente;
	private List<ProdottoQuantita> prodottiOrdinati;
	
	private String indirizzoConsegna;
	private String telefono;
	private String codiceCarta;
	private Float prezzoTotal;
	private String note;
	private Long codiceID;
	private int stato;

	/**
	 * @return the codiceID
	 */
	public Long getCodiceID() {
		return codiceID;
	}

	/**
	 * @param codiceID the codiceID to set
	 */
	public void setCodiceID(Long codiceID) {
		this.codiceID = codiceID;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the codiceCarta
	 */
	public String getCodiceCarta() {
		return codiceCarta;
	}

	/**
	 * @param codiceCarta the codiceCarta to set
	 */
	public void setCodiceCarta(String codiceCarta) {
		this.codiceCarta = codiceCarta;
	}

	/**
	 * @return the prezzoTotal
	 */
	public Float getPrezzoTotal() {
		return prezzoTotal;
	}

	/**
	 * @param prezzoTotal the prezzoTotal to set
	 */
	public void setPrezzoTotal(Float prezzoTotal) {
		this.prezzoTotal = prezzoTotal;
	}







	/**
	 * @return the indirizzoConsegna
	 */
	public String getIndirizzoConsegna() {
		return indirizzoConsegna;
	}

	/**
	 * @param indirizzoConsegna the indirizzoConsegna to set
	 */
	public void setIndirizzoConsegna(String indirizzoConsegna) {
		this.indirizzoConsegna = indirizzoConsegna;
	}








	/**
	 * @return Lista di oggetti ProdottoQuantita
	 */
	public List<ProdottoQuantita> getProdottiOrdinati() {
		return prodottiOrdinati;
	}

	/**
	 * @param prodottiOrdinati Lista di ProdottoQuantita dei prodotti ordinati
	 */
	public void setProdottiOrdinati(List<ProdottoQuantita> prodottiOrdinati) {
		this.prodottiOrdinati = prodottiOrdinati;
	}

	/**
	 * Costruttore pubblico per la Classe Ordine_Bean
	 * 
	 * @param azienda   Account Dell'Azienda dei prodotti dell'ordine
	 * @param fattorino Account del fattorino a cui viene assegnato l'ordine
	 * @param cliente   Account del cliente che ha effettuato l'Ordine
	 * @param prodottiOrdinati Lista di {@link ProdottoQuantita} con prodotti e rispettive quantità
	 */
	public Ordine_Bean(AccountAzienda_Bean azienda, AccountFattorino_Bean fattorino, AccountCliente_Bean cliente,
			List<ProdottoQuantita> prodottiOrdinati) {
		super();
		this.azienda = azienda;
		this.fattorino = fattorino;
		this.cliente = cliente;
		this.stato = 0;
		this.prodottiOrdinati = prodottiOrdinati;
	}

	/**
	 * Metodo che consente di settare lo stato dell'ordine
	 * 
	 * @param status Stringa che rappresenta lo stato (Ordine_Bean.RITIRATO,
	 *               Ordine_Bean.CONSEGNATO).
	 * 
	 */

	public void setStato(String status) {

		if (stato == 0) {
			if (status.equals(Ordine_Bean.RITIRATO))
				stato = 1;
		}
		if (stato == 1) {
			if (status.equals(Ordine_Bean.CONSEGNATO))
				stato = 2;
		}

	}

	/**
	 * 
	 * @return azienda associata all'Ordine
	 */
	public AccountAzienda_Bean getAzienda() {
		return azienda;
	}

	/**
	 * 
	 * @param azienda azienda da Associare all'ordine
	 */
	public void setAzienda(AccountAzienda_Bean azienda) {
		this.azienda = azienda;
	}

	/**
	 * 
	 * @return fattorino associato all'ordine
	 */
	public AccountFattorino_Bean getFattorino() {
		return fattorino;
	}

	/**
	 * 
	 * @param fattorino fattorino da associare all'ordine
	 */
	public void setFattorino(AccountFattorino_Bean fattorino) {
		this.fattorino = fattorino;
	}

	/**
	 * 
	 * @return cliente associato all'ordine
	 */
	public AccountCliente_Bean getCliente() {
		return cliente;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * 
	 * @param cliente cliente da associare all'ordine
	 */
	public void setCliente(AccountCliente_Bean cliente) {
		this.cliente = cliente;
	}

	/**
	 * Restituisce lo stato dell'ordine
	 * @return lo stato dell'ordine
	 */
	public String getStato() {

		if (stato == 0)
			return Ordine_Bean.IN_PREPARAZIONE;
		else if (stato == 1)
			return Ordine_Bean.RITIRATO;
		else
			return Ordine_Bean.CONSEGNATO;
	}

}

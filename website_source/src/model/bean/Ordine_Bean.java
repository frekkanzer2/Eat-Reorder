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
	private String viaDiConsegna;
	private int numConsegna;
	private String cittaConsegna;
	private String provinciaConsegna;
	private String codiceCarta;
	private Float prezzoTotal;
	private String note;
	private Long codiceID;
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
	 * @return the viaDiConsegna
	 */
	public String getViaDiConsegna() {
		return viaDiConsegna;
	}

	/**
	 * @param viaDiConsegna the viaDiConsegna to set
	 */
	public void setViaDiConsegna(String viaDiConsegna) {
		this.viaDiConsegna = viaDiConsegna;
	}

	/**
	 * @return the numConsegna
	 */
	public int getNumConsegna() {
		return numConsegna;
	}

	/**
	 * @param numConsegna the numConsegna to set
	 */
	public void setNumConsegna(int numConsegna) {
		this.numConsegna = numConsegna;
	}

	/**
	 * @return the cittaConsegna
	 */
	public String getCittaConsegna() {
		return cittaConsegna;
	}

	/**
	 * @param cittaConsegna the cittaConsegna to set
	 */
	public void setCittaConsegna(String cittaConsegna) {
		this.cittaConsegna = cittaConsegna;
	}

	/**
	 * @return the provinciaConsegna
	 */
	public String getProvinciaConsegna() {
		return provinciaConsegna;
	}

	/**
	 * @param provinciaConsegna the provinciaConsegna to set
	 */
	public void setProvinciaConsegna(String provinciaConsegna) {
		this.provinciaConsegna = provinciaConsegna;
	}

	private int stato;
	
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
	 * @param azienda Account Dell'Azienda dei prodotti dell'ordine
	 * @param fattorino Account del fattorino a cui viene assegnato l'ordine
	 * @param cliente Account del cliente che ha effettuato l'Ordine
	 */
	public Ordine_Bean(AccountAzienda_Bean azienda, AccountFattorino_Bean fattorino, AccountCliente_Bean cliente, List<ProdottoQuantita> prodottiOrdinati) {
		super();
		this.azienda = azienda;
		this.fattorino = fattorino;
		this.cliente = cliente;
		this.stato = 0;
		this.prodottiOrdinati= prodottiOrdinati;
	}
	
	/**
	 * Metodo che consente di settare lo stato dell'ordine 
	 * @param status Stringa che rappresenta lo stato (Ordine_Bean.RITIRATO, Ordine_Bean.CONSEGNATO).
	 * 
	 */
	
	public void setStato(String status) {
		
		if(stato == 0) {
			if (status.equals(Ordine_Bean.RITIRATO))
			stato = 1;
		}
		if(stato == 1) {
			if (status.equals(Ordine_Bean.CONSEGNATO))
			stato = 2;
		}
		
		
	}

	public AccountAzienda_Bean getAzienda() {
		return azienda;
	}

	public void setAzienda(AccountAzienda_Bean azienda) {
		this.azienda = azienda;
	}

	public AccountFattorino_Bean getFattorino() {
		return fattorino;
	}

	public void setFattorino(AccountFattorino_Bean fattorino) {
		this.fattorino = fattorino;
	}

	public AccountCliente_Bean getCliente() {
		return cliente;
	}

	public void setCliente(AccountCliente_Bean cliente) {
		this.cliente = cliente;
	}

	public String getStato() {
		
		if(stato ==0)
			return Ordine_Bean.IN_PREPARAZIONE;
		else if (stato == 1)
			return Ordine_Bean.RITIRATO;
		else return Ordine_Bean.CONSEGNATO;
	}

	
	
	
	
}

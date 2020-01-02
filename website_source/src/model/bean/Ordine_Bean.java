package model.bean;

import java.util.List;

public class Ordine_Bean {

	static final String IN_PREPARAZIONE = "In preparazione";
	static final String RITIRATO = "Ritirato";
	static final String CONSEGNATO = "Consegnato";
	
	private AccountAzienda_Bean azienda;
	private AccountFattorino_Bean fattorino;
	private AccountCliente_Bean cliente;
	private List<ProdottoQuantita> prodottiOrdinati;
	
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

package model;

public class Ordine {

	static final String IN_PREPARAZIONE = "In preparazione";
	static final String RITIRATO = "Ritirato";
	static final String CONSEGNATO = "Consegnato";
	
	private AccountAzienda azienda;
	private AccountFattorino fattorino;
	private AccountCliente cliente;
	
	private int stato;

	public Ordine(AccountAzienda azienda, AccountFattorino fattorino, AccountCliente cliente) {
		super();
		this.azienda = azienda;
		this.fattorino = fattorino;
		this.cliente = cliente;
		this.stato = 0;
	}
	
	public void setStato(String status) {
		
		//TODO implementazione Metodo
	}

	public AccountAzienda getAzienda() {
		return azienda;
	}

	public void setAzienda(AccountAzienda azienda) {
		this.azienda = azienda;
	}

	public AccountFattorino getFattorino() {
		return fattorino;
	}

	public void setFattorino(AccountFattorino fattorino) {
		this.fattorino = fattorino;
	}

	public AccountCliente getCliente() {
		return cliente;
	}

	public void setCliente(AccountCliente cliente) {
		this.cliente = cliente;
	}

	public String getStato() {
		
		// TODO implmentare metodo
		return null;
	}

	
	
	
	
}

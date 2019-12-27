package model.bean;

public class Ordine_Bean {

	static final String IN_PREPARAZIONE = "In preparazione";
	static final String RITIRATO = "Ritirato";
	static final String CONSEGNATO = "Consegnato";
	
	private AccountAzienda_Bean azienda;
	private AccountFattorino_Bean fattorino;
	private AccountCliente_Bean cliente;
	
	private int stato;

	public Ordine_Bean(AccountAzienda_Bean azienda, AccountFattorino_Bean fattorino, AccountCliente_Bean cliente) {
		super();
		this.azienda = azienda;
		this.fattorino = fattorino;
		this.cliente = cliente;
		this.stato = 0;
	}
	
	public void setStato(String status) {
		
		//TODO implementazione Metodo
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
		
		// TODO implmentare metodo
		return null;
	}

	
	
	
	
}

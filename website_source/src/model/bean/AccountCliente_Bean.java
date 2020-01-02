package model.bean;

public class AccountCliente_Bean extends AccountUtenteRegistrato_Bean {

	private String nome;
	private String cognome;

	/**
	 * Costruttore pubblico della classe AccountCliente_Bean
	 * 
	 * @param email email dell'Account Cliente
	 * @param password password dell'Account Cliente
	 * @param nome nome del Cliente
	 * @param cognome cognome del Cliente
	 * @throws
	 */
	public AccountCliente_Bean(String email, String password, String nome, String cognome) {
		super(email, password, AccountUtenteRegistrato_Bean.Cliente);
		this.nome = nome;
		this.cognome = cognome;
	}
	
	/**
	 * Metodo che consente la modifica dei parametri della classe AccountCliente_Bean.
	 * I dati attuali verranno modificati e sostituiti con i parametri
	 * dell'istanza passata al metodo. Non vengono modificati email e tipo di
	 * account.
	 * 
	 * @param newAccountInforma Account di tipo Azienda con le nuove informazioni
	 */

	public void modificaDati(AccountCliente_Bean newAccountInforma) {

		if (newAccountInforma != null) {
			this.setPassword(newAccountInforma.getPassword());

			this.nome = newAccountInforma.getNome();
			this.cognome = newAccountInforma.getCognome();
			
		}
	}

	/**
	 * @return nome del Cliente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome nome del Cliente da aggiornare
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return cognome del Cliente
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome cognome del Cliente da aggiornare
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	
	

}

package model.bean;

public abstract class AccountUtenteRegistrato_Bean {

	public static final String Azienda = "Azienda";
	public static final String Fattorino = "Fattorino";
	public static final String Cliente = "Cliente";
	public static final String Moderatore = "Moderatore";

	private String email;

	private String password;

	private String tipo;

	/**
	 * Costruttore per la Classe Astratta
	 * 
	 * @param email    Email dell'Account
	 * @param password password dell'Account
	 * @param tipo     tipo dell'account
	 * 
	 */
	public AccountUtenteRegistrato_Bean(String email, String password, String tipo) {
		this.email = email;
		this.password = password;
		if (tipo.equals(AccountUtenteRegistrato_Bean.Azienda)) {

			this.tipo = AccountUtenteRegistrato_Bean.Azienda;
		} else if (tipo.equals(AccountUtenteRegistrato_Bean.Fattorino)) {
			this.tipo = AccountUtenteRegistrato_Bean.Fattorino;
		} else if (tipo.equals(AccountUtenteRegistrato_Bean.Cliente)) {

			this.tipo = AccountUtenteRegistrato_Bean.Cliente;
		} else if (tipo.equals(AccountUtenteRegistrato_Bean.Moderatore)) {
			this.tipo = AccountUtenteRegistrato_Bean.Moderatore;
		}

	}

	/**
	 * 
	 * @return email dell'Account
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email email dell'Account
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return password dell'Account
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password password dell'Account
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return tipo dell'account (Azienda, Fattorino, Cliente, Moderatore)
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo tipo di account tra Azienda,Fattorino,Moderatore,Cliente
	 * 
	 */
	public void setTipo(String tipo) {
		if (tipo.equals(AccountUtenteRegistrato_Bean.Azienda)) {

			this.tipo = AccountUtenteRegistrato_Bean.Azienda;
		} else if (tipo.equals(AccountUtenteRegistrato_Bean.Fattorino)) {
			this.tipo = AccountUtenteRegistrato_Bean.Fattorino;
		} else if (tipo.equals(AccountUtenteRegistrato_Bean.Cliente)) {

			this.tipo = AccountUtenteRegistrato_Bean.Cliente;
		} else if (tipo.equals(AccountUtenteRegistrato_Bean.Moderatore)) {
			this.tipo = AccountUtenteRegistrato_Bean.Moderatore;

		}

	}
}

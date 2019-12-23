package model;

public abstract class  AccountUtenteRegistrato {
	
	static final String Azienda = "Azienda";
	static final String Fattorino ="Fattorino";
	static final String Cliente ="Cliente";
	static final String Moderatore ="Moderatore";
	

	private String email;

	private String password;
	
	private String tipo;
	

	public AccountUtenteRegistrato(String email, String password, String tipo) {
		this.email = email;
		this.password = password;
		this.tipo = tipo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}

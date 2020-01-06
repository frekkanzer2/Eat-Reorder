package model;
/**
 * Classe con metodi statici che consente la verifica dei dati di varie entitא
 * @author Rosario
 *
 */

public class CheckFormato {
		
	
	public boolean formatoRegistrazioneCliente(String email,String password,String nome,String cognome) {
		boolean in_email = email.matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean in_password = password.matches("[a-zA-Z0-9]{7,20}");
		boolean in_nome = nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_cognome = cognome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		if (in_email == true && in_password == true && in_nome == true && in_cognome == true) 
			return true;
		return false;
	}
}

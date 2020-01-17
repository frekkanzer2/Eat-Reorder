package model.bean;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountFattorino_Bean extends AccountUtenteRegistrato_Bean {

	private String nome;
	private String cognome;
	private String telefono;
	private String cittaConsegna;
	private String provinciaConsegna;
	private LocalTime inizioConsegne;
	private LocalTime fineConsegne;
	private Set<DayOfWeek> giorniDiConsegna = new HashSet<DayOfWeek>();
	
	/**
	 * Costruttore pubblico per la classe AccountFattorino_Bean
	 * @param email email del Fattorino	
	 * @param password password del Fattorino
	 * @param nome nome del Fattorino
	 * @param cognome cognome del Fattorino
	 * @param telefono telefono del Fattorino
	 * @param cittaConsegna citta' di consegna del Fattorino
	 * @param provinciaConsegna provincia di consegna del Fattorino
	 * @param inizioConsegne orario di inizio consegne
	 * @param fineConsegne orario di fine consegne
	 * @param giorniDiConsegna Lista di giorni della settimana in cui il fattorino è disponibile a consegnare
	 */
	public AccountFattorino_Bean(String email, String password, String nome, String cognome, String telefono,
			String cittaConsegna, String provinciaConsegna, LocalTime inizioConsegne, LocalTime fineConsegne,
			List<DayOfWeek> giorniDiConsegna) {
		super(email, password, AccountUtenteRegistrato_Bean.Fattorino);
			this.cognome= cognome;
			this.nome = nome;
			this.telefono = telefono;
			this.cittaConsegna = cittaConsegna;
			this.provinciaConsegna = provinciaConsegna;
			this.inizioConsegne = inizioConsegne;
			this.fineConsegne = fineConsegne;
			
			if(giorniDiConsegna!= null) {
				for(DayOfWeek x : giorniDiConsegna)
					this.giorniDiConsegna.add(x);
				
			}
			
	}
	
	/**
	 *  Metodo che consente la modifica dei parametri della classe AccountFattorino_Bean.
	 * I dati attuali verranno modificati e sostituiti con i parametri
	 * dell'istanza passata al metodo. Non vengono modificati email e tipo di
	 * account.
	 * 
	 * @param newAccountInforma {@link AccountFattorino_Bean}
	 */
	public void modificaDati(AccountFattorino_Bean newAccountInforma) {

		if (newAccountInforma != null) {
			this.setPassword(newAccountInforma.getPassword());

			this.cognome= newAccountInforma.getCognome();
			this.nome = newAccountInforma.getNome();
			this.telefono = newAccountInforma.getTelefono();
			this.cittaConsegna = newAccountInforma.getCittaConsegna();
			this.provinciaConsegna = newAccountInforma.getProvinciaConsegna();
			this.inizioConsegne = newAccountInforma.getInizioConsegne();
			this.fineConsegne = newAccountInforma.getFineConsegne();
			

			if (newAccountInforma.getGiorniDiConsegna()!= null)
				giorniDiConsegna.clear();
			for (DayOfWeek x : newAccountInforma.getGiorniDiConsegna()) {
				this.giorniDiConsegna.add(x);
			}
		}
	}

	/**
	 * @return Il nome del Fattorino
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome IL nome del fattorino
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return cognome del Fattorino
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome cognome del Fattorino
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return numero di telefono del fattorino
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono numero di telefono del fattorino
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return Città di consegna del fattorino
	 */
	public String getCittaConsegna() {
		return cittaConsegna;
	}

	/**
	 * @param cittaConsegna Città di consegna del fattorino
	 */
	public void setCittaConsegna(String cittaConsegna) {
		this.cittaConsegna = cittaConsegna;
	}

	/**
	 * @return provincia di consegna del fattorino
	 */
	public String getProvinciaConsegna() {
		return provinciaConsegna;
	}

	/**
	 * @param provinciaConsegna provincia di consegna del fattorino
	 */
	public void setProvinciaConsegna(String provinciaConsegna) {
		this.provinciaConsegna = provinciaConsegna;
	}

	/**
	 * @return Orario di inizio consegne del fattorino
	 */
	public LocalTime getInizioConsegne() {
		return inizioConsegne;
	}

	/**
	 * @param inizioConsegne orario di inizio consegne del fattorino
	 */
	public void setInizioConsegne(LocalTime inizioConsegne) {
		this.inizioConsegne = inizioConsegne;
	}

	/**
	 * @return orario di fine consegne del fattorino
	 */
	public LocalTime getFineConsegne() {
		return fineConsegne;
	}

	/**
	 * @param fineConsegne orario di fine consegne del fattorino
	 */
	public void setFineConsegne(LocalTime fineConsegne) {
		this.fineConsegne = fineConsegne;
	}

	/**
	 * @return Set con i giorni della settimana in cui il fattorino è disponibile alle consegne
	 */
	public Set<DayOfWeek> getGiorniDiConsegna() {
		return giorniDiConsegna;
	}

	/**
	 * Questo metodo aggiorna i giorni della settimana in cui il fattorino è 
	 * disponibile alla consegna. I nuovi giorni disponibili saranno quelli passati
	 * al metodo tramite la lista. I precedenti giorni saranno sovrascritti
	 * @param giorniDiConsegna Lista di giorni della settimana in cui il fattorino è disponibile alle consegne
	 */
	public void setGiorniDiConsegna(List<DayOfWeek> giorniDiConsegna) {
		if(giorniDiConsegna!= null) {
			this.giorniDiConsegna.clear();
			for(DayOfWeek x : giorniDiConsegna)
				this.giorniDiConsegna.add(x);
			
		}
	}
	
	

}

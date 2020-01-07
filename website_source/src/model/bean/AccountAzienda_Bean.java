package model.bean;

import java.time.DayOfWeek;
import java.time.LocalTime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountAzienda_Bean extends AccountUtenteRegistrato_Bean {

	private String nome;

	private String via;

	private int numeroCivico;

	private String citta;

	private String provincia;

	private String telefono;

	private String partitaIva;

	private LocalTime orarioDiApertura;

	private LocalTime orarioDiChiusura;

	private Set<DayOfWeek> giorniDiApertura = new HashSet<DayOfWeek>();

	private Map<Long, Prodotto_Bean> prodotti = new HashMap<Long, Prodotto_Bean>();

	/**
	 * Costruttore pubblico per la classe AccountAzienda.
	 * 
	 * @param email            Email dell'azienda
	 * @param password         Password dell'azienda
	 * @param nome             Nome dell'azienda
	 * @param via              Via dell'azienda
	 * @param numeroCivico     Numero Civico dell'azienda
	 * @param citta            Città dell'azienda
	 * @param provincia        Provincia dell'azienda (Due lettere)
	 * @param telefono         Telefono dell'azienda
	 * @param partitaIva       Partita iva dell'azienda
	 * @param orarioDiApertura Orario di Apertura
	 * @param orarioDiChiusura Orario di Chiusura
	 * @param giorniDiApertura Giorni di Apertura(Lunedì-Sabato)
	 * 
	 */
	public AccountAzienda_Bean(String email, String password, String nome, String via, int numeroCivico, String citta,
			String provincia, String telefono, String partitaIva, LocalTime orarioDiApertura,
			LocalTime orarioDiChiusura, List<DayOfWeek> giorniDiApertura) {

		super(email, password, AccountUtenteRegistrato_Bean.Azienda);
		this.nome = nome;
		this.via = via;
		this.numeroCivico = numeroCivico;
		this.citta = citta;
		this.provincia = provincia;
		this.telefono = telefono;
		this.partitaIva = partitaIva;
		this.orarioDiApertura = orarioDiApertura;
		this.orarioDiChiusura = orarioDiChiusura;

		if (giorniDiApertura != null) {
			for (DayOfWeek x : giorniDiApertura) {
				this.giorniDiApertura.add(x);
			}
		}

	}

	/**
	 * Metodo che consente la modifica dei parametri della classe AccountAzienda. I
	 * dati I dati attuali verranno modificati e sostituiti con i parametri
	 * dell'istanza passata al metodo. Non vengono modificati email e tipo di
	 * account.
	 * 
	 * @param newAccountInforma Account di tipo Azienda con le nuove informazioni
	 */

	public void modificaDati(AccountAzienda_Bean newAccountInforma) {

		if (newAccountInforma != null) {
			this.setPassword(newAccountInforma.getPassword());

			this.nome = newAccountInforma.getNome();
			this.via = newAccountInforma.getVia();
			this.numeroCivico = newAccountInforma.getNumeroCivico();
			this.citta = newAccountInforma.getCitta();
			this.provincia = newAccountInforma.getProvincia();
			this.telefono = newAccountInforma.getTelefono();
			this.partitaIva = newAccountInforma.partitaIva;
			this.orarioDiApertura = newAccountInforma.getOrarioDiApertura();
			this.orarioDiChiusura = newAccountInforma.getOrarioDiChiusura();

			if (newAccountInforma.getGiorniDiApertura() != null)
				this.giorniDiApertura = newAccountInforma.getGiorniDiApertura();
		}
	}

	/**
	 * @return Map dei prodotti.
	 */
	public Map<Long, Prodotto_Bean> getProdotti() {
		return prodotti;
	}

	/**
	 * Metodo che consente di ricevere in output un prodotto appartenente al listino
	 * dell'azienda. Il parametro in input è il codice del prodotto.
	 * 
	 * @param id Codice del prodotto
	 * @return Viene restituito il prodotto dell'Azienda con l'ID inserito o il
	 *         valore null se l'oggetto non è presente
	 */

	public Prodotto_Bean dammiProdotto(Long id) {

		if (prodotti.containsKey(id)) {
			return prodotti.get(id);
		} else
			return null;
	}

	/**
	 * Aggiunge un prodotto al listino dell'Azienda
	 * 
	 * @param p istanza di Prodotto_Bean da inserire nel listino
	 */
	public void aggiungiProdotto(Prodotto_Bean p) {

		if (p != null)
			prodotti.putIfAbsent(p.getCodice(), p);
	}

	/**
	 * Aggiorna i dati di un prodotto se presente nel listino
	 * 
	 * @param p istanza di ProdttoBean con i dati aggiornati
	 */
	public void aggiornaProdotto(Prodotto_Bean p) {

		Prodotto_Bean x = prodotti.get(p.getCodice());
		if (x != null) {
			x.setDescrizione(p.getDescrizione());
			x.setImmagine(p.getImmagine());
			x.setNome(p.getNome());
			x.setPrezzo(p.getPrezzo());
		}

	}

	/**
	 * Rimuove un prodotto dal listino dell'azienda se presente
	 * 
	 * @param p istanza di ProdottoBean da eliminare
	 */

	public void rimuoviProdotto(Prodotto_Bean p) {

		if (p != null)
			prodotti.remove(p.getCodice());
	}

	/**
	 * 
	 * @return nome dell'Azienda
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome Nome dell'Azienda
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return via dell'Azienda
	 */
	public String getVia() {
		return via;
	}

	/**
	 * 
	 * @param via Via dell'Azienda
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/**
	 * 
	 * @return numero civico dell'Azienda
	 */
	public int getNumeroCivico() {
		return numeroCivico;
	}

	/**
	 * 
	 * @param numeroCivico numero civico dell'Azienda
	 */
	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	/**
	 * 
	 * @return città dell'Azienda
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * 
	 * @param citta Città dell'Azienda
	 */

	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * 
	 * @return provincia dell'Azienda
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * 
	 * @param provincia provincia dell'Azienda
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * 
	 * @return telefono dell'Azienda
	 */

	public String getTelefono() {
		return telefono;
	}

	/**
	 * 
	 * @param telefono telefono dell'Azienda
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * 
	 * @return partita IVA dell'Azienda
	 */

	public String getPartitaIva() {
		return partitaIva;
	}

	/**
	 * 
	 * @param partitaIva Partita IVA dell'Azienda
	 */

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	/**
	 * 
	 * @return Orario di Apertura dell'azienda espresso in HH:MM:SS
	 */
	public LocalTime getOrarioDiApertura() {
		return orarioDiApertura;
	}

	/**
	 * 
	 * @param orarioDiApertura LocalTime indicato con HH:MM:SS
	 */

	public void setOrarioDiApertura(LocalTime orarioDiApertura) {
		this.orarioDiApertura = orarioDiApertura;
	}

	/**
	 * 
	 * @return Orario di Chiusura dell'azienda espresso in HH:MM:SS
	 */
	public LocalTime getOrarioDiChiusura() {
		return orarioDiChiusura;
	}

	/**
	 * 
	 * @param orarioDiChiusura LocalTime indicato con HH:MM:SS
	 */

	public void setOrarioDiChiusura(LocalTime orarioDiChiusura) {
		this.orarioDiChiusura = orarioDiChiusura;
	}

	/**
	 * 
	 * @return Set di DayOfWeek corrispondenti ai giorni di apertura dell'Azienda
	 */

	public Set<DayOfWeek> getGiorniDiApertura() {
		return giorniDiApertura;
	}

	/**
	 * 
	 * @param giorniDiApertura Set di DayOfWeek che rappresentano i giorni di
	 *                         apertura
	 */
	public void setGiorniDiApertura(List<DayOfWeek> giorniDiApertura) {
		if (giorniDiApertura != null) {
			this.giorniDiApertura.clear();
			for (DayOfWeek x : giorniDiApertura)
				this.giorniDiApertura.add(x);
		}

	}
}

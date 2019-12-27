package model.bean;

import java.time.DayOfWeek;
import java.time.LocalTime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountAzienda_Bean extends AccountUtenteRegistrato_Bean{
	
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
	
	private Map<Long,Prodotto_Bean> prodotti = new HashMap<Long,Prodotto_Bean>();

	
	public AccountAzienda_Bean(String email, String password, String tipo, String nome, String via, int numeroCivico, String citta,
			String provincia, String telefono, String partitaIva, LocalTime orarioDiApertura,
			LocalTime orarioDiChiusura, List<DayOfWeek> giorniDiApertura) {
		
		super(email,password,tipo);
		this.nome = nome;
		this.via = via;
		this.numeroCivico = numeroCivico;
		this.citta = citta;
		this.provincia = provincia;
		this.telefono = telefono;
		this.partitaIva = partitaIva;
		this.orarioDiApertura = orarioDiApertura;
		this.orarioDiChiusura = orarioDiChiusura;
		
		for(DayOfWeek x : giorniDiApertura){
			this.giorniDiApertura.add(x);
		}
	}
	
	public void modificaDati(AccountAzienda_Bean newAccountInforma) {
		
	}
	
	
	public Prodotto_Bean dammiProdotto(Long id) {
		
		//TODO implementazione metodo
		return null;
	}
	
	public void aggiungiProdotto(Prodotto_Bean p) {
		
		//TODO implementazione metodo
	}
	
	public void aggiornaProdotto(Prodotto_Bean p ) {
		
		//TODO implmentazione metodo
	}
	
	public void rimuoviProdotto(Prodotto_Bean p) {
		
		//TODO implementazione metodo
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public LocalTime getOrarioDiApertura() {
		return orarioDiApertura;
	}

	public void setOrarioDiApertura(LocalTime orarioDiApertura) {
		this.orarioDiApertura = orarioDiApertura;
	}

	public LocalTime getOrarioDiChiusura() {
		return orarioDiChiusura;
	}

	public void setOrarioDiChiusura(LocalTime orarioDiChiusura) {
		this.orarioDiChiusura = orarioDiChiusura;
	}

	public Set<DayOfWeek> getGiorniDiApertura() {
		return giorniDiApertura;
	}

	public void setGiorniDiApertura(Set<DayOfWeek> giorniDiApertura) {
		this.giorniDiApertura = giorniDiApertura;
	}
	
	

}

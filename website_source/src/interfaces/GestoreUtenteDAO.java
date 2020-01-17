package interfaces;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import model.DBConnectionPool;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountModeratore_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Ordine_Bean;
import model.bean.Prodotto_Bean;

	/*
	 * Interfaccia per il gestore Utente Dao
	 */
public interface GestoreUtenteDAO {
	
	/**
	 * 
	 * @param email email di un utente
	 * @return true se l'email è associata ad un account, false altrimenti
	 * @throws SQLException
	 */

	public boolean controlloEsistenzaMail(String email) throws SQLException;
	/**
	 * 
	 * @param cliente AccountCliente_Bean di cui effettuare la registrazione
	 * @throws SQLException
	 */

	public void registrazioneCliente(AccountCliente_Bean cliente) throws SQLException;
	/**
	 * 
	 * @param azienda AccountAzienda_Bean di cui effettuare la registrazione
	 * @throws SQLException
	 */

	public void registrazioneAzienda(AccountAzienda_Bean azienda) throws SQLException;
	/**
	 * 
	 * @param fattorino AccountFattorino_Bean di cui effettuare la registrazione
	 * @throws SQLException
	 */

	public void registrazioneFattorino(AccountFattorino_Bean fattorino) throws SQLException;
	/**
	 * 
	 * @param email di cui controllare il ban
	 * @return true se l'account associato all'email risulta bannato, false altrimenti
	 * @throws SQLException
	 */

	public boolean controllaBan(String email) throws SQLException;
	
	/**
	 * 
	 * @param user username associata ad un account
	 * @param password password da verificare
	 * @return true se esiste un account con l'email e password inserite, false altrimenti
	 * @throws SQLException
	 */

	public boolean controllaEsistenzaAccount(String user, String password) throws SQLException;
	/**
	 * 
	 * @param email email associata ad un {@link AccountUtenteRegistrato_Bean} 
	 * @return AccountUtenteRegistrato_Bean corrispondente alla email indicata
	 * @throws SQLException
	 */

	public AccountUtenteRegistrato_Bean dammiUtente(String email) throws SQLException;
	
	/**
	 * 
	 * @param cliente istanza di AccountCliente_Bean contenente i dati modificati 
	 * @throws SQLException
	 */

	public void aggiornaCliente(AccountCliente_Bean cliente) throws SQLException;
	/**
	 * 
	 * @param azienda istanza di AccountAzienda_Bean contenente i dati modificati 
	 * @throws SQLException
	 */

	public void aggiornaAzienda(AccountAzienda_Bean azienda) throws SQLException;
	/**
	 * 
	 * @param fattorino istanza di AccountFattorino_Bean contenente i dati modificati 
	 * @throws SQLException
	 */

	public void aggiornaFattorino(AccountFattorino_Bean fattorino) throws SQLException;
	/**
	 * 
	 * @param codiceOrdine codice associato ad un ordine
	 * @return AccountAzienda_Bean associata all'ordine con il codice inserito
	 * @throws SQLException
	 */

	public AccountAzienda_Bean dammiAziendaConOrdine(Long codiceOrdine) throws SQLException;
	
	/**
	 * 
	 * @param citta Città di cui effettuare la ricerca
	 * @return List<AccountAzienda_Bean> contenente le azienda che hanno la sede nella città inserita
	 * @throws SQLException
	 */

	public List<AccountAzienda_Bean> dammiListaAziende(String citta) throws SQLException;
	/**
	 * 
	 * @param azienda AccountAzienda_Bean di cui effettuare il ban
	 * @throws SQLException
	 */

	public void banAzienda(AccountAzienda_Bean azienda) throws SQLException;
	/**
	 * 
	 * @param azienda AccountAzienda_Bean a cui aggiungere un prodotto
	 * @param prodotto Prodotto_Bean da aggiungere al listino dell'azienda
	 * @throws SQLException
	 */

	public void aggiungiAlListino(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException;
	
	/**
	 * 
	 * @param azienda AccountAzienda_Bean a cui aggiornare un prodotto
	 * @param prodotto Prodotto_Bean con i dati aggiornati
	 * @throws SQLException
	 */

	public void aggiornaProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException;
	
	/**
	 * 
	 * @param azienda AccountAzienda_Bean a cui rimuovere un prodotto
	 * @param prodotto Prodotto_Bean da rimuovere
	 * @throws SQLException
	 */

	public void rimuoviProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException;
}

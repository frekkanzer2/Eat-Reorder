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

	public boolean controlloEsistenzaMail(String email) throws SQLException;

	public void registrazioneCliente(AccountCliente_Bean cliente) throws SQLException;

	public void registrazioneAzienda(AccountAzienda_Bean azienda) throws SQLException;

	public void registrazioneFattorino(AccountFattorino_Bean fattorino) throws SQLException;

	public boolean controllaBan(String email) throws SQLException;

	public boolean controllaEsistenzaAccount(String user, String password) throws SQLException;

	public AccountUtenteRegistrato_Bean dammiUtente(String email) throws SQLException;

	public void aggiornaCliente(AccountCliente_Bean cliente) throws SQLException;

	public void aggiornaAzienda(AccountAzienda_Bean azienda) throws SQLException;

	public void aggiornaFattorino(AccountFattorino_Bean fattorino) throws SQLException;

	public AccountAzienda_Bean dammiAziendaConOrdine(Long codiceOrdine) throws SQLException;

	public List<AccountAzienda_Bean> dammiListaAziende(String citta) throws SQLException;

	public void banAzienda(AccountAzienda_Bean azienda) throws SQLException;

	public void aggiungiAlListino(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException;

	public void aggiornaProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException;

	public void rimuoviProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException;
}

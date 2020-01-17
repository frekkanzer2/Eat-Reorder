package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Carrello;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.Ordine_Bean;

/*
 * Interfaccia per il GestoreOrdineDao
 */

public interface GestoreOrdineDao {
	
	/**
	 * 
	 * @param order istanza di Ordine_Bean
	 * @throws SQLException 
	 * @throws Exception
	 */

	public void creaOrdine(Ordine_Bean order) throws SQLException, Exception;
	
	/**
	 * 
	 * @param idOrdine id dell'ordine
	 * @return true se l'ordine esiste, false altrimenti
	 * @throws SQLException 
	 */

	public boolean controlloEsistenzaOrdine(Long idOrdine) throws SQLException;
	/**
	 * 
	 * @param azienda AccountAzienda_Bean di cui avere la lista di ordini
	 * @return List<Ordine_Bean> con gli ordini in preparazione dell'azienda
	 * @throws SQLException
	 */

	public List<Ordine_Bean> dammiOrdiniPreparazione(AccountAzienda_Bean azienda) throws SQLException;
	/**
	 * 
	 * @param idOrdine codice dell'ordine
	 * @return Ordine_Bean con il codice idOrdine
	 * @throws SQLException
	 */

	public Ordine_Bean dammiOrdine(Long idOrdine) throws SQLException;
	/**
	 * 
	 * @param fattorino AccountFattorino_Bean di cui avere le consegne da effettuare
	 * @return List<Ordine_Bean> che contiene gli ordini assegnati ad un fattorino
	 * @throws SQLException
	 */

	public List<Ordine_Bean> dammiConsegne(AccountFattorino_Bean fattorino) throws SQLException;
	
	/**
	 * 
	 * @param idOrdine id dell'ordine di cui settare lo stato a ritirato
	 * @throws SQLException
	 */

	public void ordineSetRitirato(Long idOrdine) throws SQLException;
	/**
	 * 
	 * @param idOrdine id dell'ordine di cui settare lo stato a consegnato
	 * @throws SQLException
	 */

	public void ordineSetConsegnato(Long idOrdine) throws SQLException;
}

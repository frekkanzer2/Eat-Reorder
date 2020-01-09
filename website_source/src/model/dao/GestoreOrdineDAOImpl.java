package model.dao;

import java.beans.Statement;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.dbunit.dataset.sqlloader.SqlLoaderControlDataSet;
import org.javatuples.Pair;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

import interfaces.GestoreOrdineDao;
import interfaces.GestoreUtenteDAO;
import model.Carrello;
import model.DBConnectionPool;
import model.ProdottoQuantita;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.Ordine_Bean;
import model.bean.Prodotto_Bean;

public class GestoreOrdineDAOImpl implements GestoreOrdineDao {

	private Connection connect;

	// terminato
	/**
	 * Metodo che consente la creazione di Ordine a db.
	 * 
	 * @param order Ordine_Bean che contiene tutte le informazioni riguardante un
	 *              ordine
	 */
	public void creaOrdine(Ordine_Bean order) throws SQLException, Exception {

		connect = DBConnectionPool.getConnection();

		Date date = new Date();
		DayOfWeek x = DayOfWeek.of(date.getDay());
		LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

		AccountAzienda_Bean az = order.getAzienda();
		if (!az.getGiorniDiApertura().contains(x))
			throw new Exception("L'azienda è chiusa oggi e non può evadere il tuo ordine");
		if (time.isAfter(az.getOrarioDiChiusura()) || time.isBefore(az.getOrarioDiApertura()))
			throw new Exception("L'azienda è chiusa adesso e non può evadere il tuo ordine");

		PreparedStatement stmt = connect.prepareStatement(
				"select fattorino.email, fattorino.nome, giorniLavorativi.giorno, fattorino.orario_inizio, fattorino.orario_fine from fattorino, giorniLavorativi where fattorino.citta_consegna=? and giorniLavorativi.giorno=? and fattorino.orario_inizio< ? and fattorino.orario_fine > ?");
		stmt.setString(1, order.getAzienda().getCitta());
		stmt.setString(2, x.toString());
		stmt.setString(3, time.toString());
		stmt.setString(4, time.toString());

		ResultSet set = stmt.executeQuery();
		ArrayList<Pair<String, String>> listOfEmailFattorino;
		if (!set.next())
			throw new Exception("Nessun Fattorino Disponibile");
		else {
			listOfEmailFattorino = new ArrayList<Pair<String, String>>();
			listOfEmailFattorino.add(new Pair<String, String>(set.getString("email"), set.getString("nome")));
			while (set.next()) {
				listOfEmailFattorino.add(new Pair<String, String>(set.getString("email"), set.getString("nome")));
			}

		}

		Random randomizer = new Random();
		Pair<String, String> fattorino = listOfEmailFattorino.get(randomizer.nextInt(listOfEmailFattorino.size()));

		connect.setAutoCommit(false);
		Savepoint save = connect.setSavepoint();

		try {

			stmt = connect.prepareStatement(
					"insert into ordine (indirizzo_consegna, numero_carta, prezzo_totale, note, stato, acquirente, email_acquirente, azienda, email_azienda, fattorino, email_fattorino) values "
							+ "(?,?,?,?,?,?,?,?,?,?,?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,order.getIndirizzoConsegna());
			stmt.setString(2, order.getCodiceCarta());
			stmt.setFloat(3, order.getPrezzoTotal());
			stmt.setString(4, order.getNote());
			stmt.setString(5, order.getStato());
			stmt.setString(6, order.getCliente().getNome());
			stmt.setString(7, order.getCliente().getEmail());
			stmt.setString(9, order.getAzienda().getEmail());
			stmt.setString(8, order.getAzienda().getNome());
			stmt.setString(10, fattorino.getValue1());
			stmt.setString(11, fattorino.getValue0());

			long idOrder = stmt.executeUpdate();
			System.out.println(idOrder);
			order.setCodiceID(idOrder);
			for (ProdottoQuantita pq : order.getProdottiOrdinati()) {
				stmt = connect.prepareStatement("insert into prodottoordine (quantita,prodotto,ordine) values (?,?,?)");
				stmt.setInt(1, pq.getQta());
				stmt.setLong(2, pq.getProdotto().getCodice());
				stmt.setLong(3, idOrder);
				stmt.executeUpdate();
			}

			connect.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			connect.rollback(save);
			throw new SQLException(e.getMessage());
		} finally {
			connect.setAutoCommit(true);
		}

	}

	// terminato
	/**
	 * Metodo che consente di conoscere l'esistenza di un ordine in base all'ID
	 * 
	 * @param idOrdine
	 * @return True se esiste un ordine con l'IdOrdine inserito altrimenti false
	 * @throws SQLException
	 */

	public boolean controlloEsistenzaOrdine(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select * from Ordine where Ordine.codice=?");
		stmt.setLong(1, idOrdine);

		ResultSet x = stmt.executeQuery();

		if (x.next()) {
			return true;
		}

		return false;

	}

	//terminato
	/**
	 * Questo metodo restituisce gli ordini dell'azienda con all'interno solo i prodotti e le quantità. Non restituisce gli account associati.
	 */
	public List<Ordine_Bean> dammiOrdiniPreparazione(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select * from Ordine where email_azienda = ? and stato = ?");
		stmt.setString(1, azienda.getEmail());
		stmt.setString(2, Ordine_Bean.IN_PREPARAZIONE);

		ResultSet x = stmt.executeQuery();
		List<Ordine_Bean> lista = new ArrayList<Ordine_Bean>();
		while (x.next()) {
			Long idOrdine = x.getLong("codice");
			String indirizzoConsegna = x.getString("indirizzo_consegna");
			String codiceCarta = x.getString("numero_carta");
			Float prezzoTotale = x.getFloat("prezzo_totale");
			String note = x.getString("note");
			String stato = x.getString("stato");

			List<ProdottoQuantita> prodottiOrdinati = new ArrayList<ProdottoQuantita>();
			PreparedStatement stmt2 = connect.prepareStatement(
					"SELECT * FROM eatreorder.prodottoordine join prodotto on prodotto.codice = prodottoordine.prodotto and prodottoordine.ordine = ?;");
			stmt2.setLong(1, idOrdine);
			ResultSet set = stmt2.executeQuery();

			while (set.next()) {

				Prodotto_Bean prod = new Prodotto_Bean();
				prod.setCodice(set.getLong("codice"));
				prod.setAzienda(azienda);
				prod.setDescrizione(set.getString("descrizione"));
				try {
					prod.setImmagine(new URL(set.getString("path_immagine")));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				prod.setNome(set.getString("nome"));
				prod.setPrezzo(set.getFloat("prezzo"));
				
				ProdottoQuantita prodq = new ProdottoQuantita();
				prodq.setProdotto(prod);
				prodq.setQta(set.getInt("quantita"));

				prodottiOrdinati.add(prodq);

			}

			Ordine_Bean ordine = new Ordine_Bean(null, null, null, prodottiOrdinati);
			ordine.setCodiceID(idOrdine);
			ordine.setCodiceCarta(codiceCarta);
			ordine.setIndirizzoConsegna(indirizzoConsegna);
			ordine.setNote(note);
			ordine.setPrezzoTotal(prezzoTotale);
			ordine.setStato(stato);
			
			lista.add(ordine);
		}

		return lista;
	}

	//terminato
	public Ordine_Bean dammiOrdine(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select * from Ordine where Ordine.codice=?");
		stmt.setLong(1, idOrdine);

		ResultSet x = stmt.executeQuery();

		if (x.next()) {

			String indirizzoConsegna = x.getString("indirizzo_consegna");
			String codiceCarta = x.getString("numero_carta");
			Float prezzoTotale = x.getFloat("prezzo_totale");
			String note = x.getString("note");
			String stato = x.getString("stato");

			String emAzienda = x.getString("email_azienda");
			String emFattorino = x.getString("email_fattorino");
			String emCliente = x.getString("email_acquirente");

			GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();

			AccountAzienda_Bean azienda = (AccountAzienda_Bean) dao.dammiUtente(emAzienda);
			AccountFattorino_Bean fattorino = (AccountFattorino_Bean) dao.dammiUtente(emFattorino);
			AccountCliente_Bean cliente = (AccountCliente_Bean) dao.dammiUtente(emCliente);

			List<ProdottoQuantita> prodottiOrdinati = new ArrayList<ProdottoQuantita>();
			PreparedStatement stmt2 = connect.prepareStatement(
					"SELECT * FROM eatreorder.prodottoordine join prodotto on prodotto.codice = prodottoordine.prodotto and prodottoordine.ordine = ?;");
			stmt2.setLong(1, idOrdine);
			ResultSet set = stmt2.executeQuery();

			while (set.next()) {

				Prodotto_Bean prod = new Prodotto_Bean();
				prod.setCodice(set.getLong("codice"));
				prod.setAzienda(azienda);
				prod.setDescrizione(set.getString("descrizione"));
				try {
					prod.setImmagine(new URL(set.getString("path_immagine")));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				prod.setNome(set.getString("nome"));
				prod.setPrezzo(set.getFloat("prezzo"));
				
				ProdottoQuantita prodq = new ProdottoQuantita();
				prodq.setProdotto(prod);
				prodq.setQta(set.getInt("quantita"));

				prodottiOrdinati.add(prodq);

			}

			Ordine_Bean ordine = new Ordine_Bean(azienda, fattorino, cliente, prodottiOrdinati);
			ordine.setCodiceID(idOrdine);
			ordine.setCodiceCarta(codiceCarta);
			ordine.setIndirizzoConsegna(indirizzoConsegna);
			ordine.setNote(note);
			ordine.setPrezzoTotal(prezzoTotale);
			ordine.setStato(stato);
			
			return ordine;
		}

		return null;
	}
	
	//terminato
	/**
	 * Metodo che consente di avere la lista degli ordini da consegnare(in preparazione/ritirati). All'ordine sono associati tutti gli 
	 * account ma non viene associato l'elenco dei prodotti contenuti. 
	 */

	public List<Ordine_Bean> dammiConsegne(AccountFattorino_Bean fattorino) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select * from Ordine where email_fattorino = ? and (stato = ? or stato = ?)");
		stmt.setString(1, fattorino.getEmail());
		stmt.setString(2, Ordine_Bean.IN_PREPARAZIONE);
		stmt.setString(3, Ordine_Bean.RITIRATO);

		ResultSet x = stmt.executeQuery();
		List<Ordine_Bean> lista = new ArrayList<Ordine_Bean>();
		while (x.next()) {
			Long idOrdine = x.getLong("codice");
			String indirizzoConsegna = x.getString("indirizzo_consegna");
			String codiceCarta = x.getString("numero_carta");
			Float prezzoTotale = x.getFloat("prezzo_totale");
			String note = x.getString("note");
			String stato = x.getString("stato");
			String emAzienda = x.getString("email_azienda");
			
			String emCliente = x.getString("email_acquirente");

			GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();

			AccountAzienda_Bean azienda = (AccountAzienda_Bean) dao.dammiUtente(emAzienda);
			AccountCliente_Bean cliente = (AccountCliente_Bean) dao.dammiUtente(emCliente);


			Ordine_Bean ordine = new Ordine_Bean(azienda, fattorino, cliente, null);
			ordine.setCodiceID(idOrdine);
			ordine.setCodiceCarta(codiceCarta);
			ordine.setIndirizzoConsegna(indirizzoConsegna);
			ordine.setNote(note);
			ordine.setPrezzoTotal(prezzoTotale);
			ordine.setStato(stato);
			
			lista.add(ordine);
		}

		return lista;
	}

	//terminato
	public void ordineSetRitirato(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("update Ordine set Ordine.stato=?");
		stmt.setString(1, Ordine_Bean.RITIRATO);

		stmt.executeUpdate();

		return;
	}
	
	//terminato

	public void ordineSetConsegnato(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("update Ordine set Ordine.stato=?");
		stmt.setString(1, Ordine_Bean.CONSEGNATO);

		stmt.executeUpdate();

		return;
	}

}

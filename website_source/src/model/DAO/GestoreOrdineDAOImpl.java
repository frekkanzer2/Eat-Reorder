package model.DAO;

import java.beans.Statement;
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
import model.Carrello;
import model.DBConnectionPool;
import model.ProdottoQuantita;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.Ordine_Bean;

public class GestoreOrdineDAOImpl implements GestoreOrdineDao {

	private Connection connect;

	/**
	 * Metodo che consente la creazione di Ordine a db.
	 * @param order Ordine_Bean che contiene tutte le informazioni riguardante un ordine
	 */
	public void creaOrdine(Ordine_Bean order) throws SQLException, Exception {

		connect = DBConnectionPool.getConnection();

		Date date = new Date();
		DayOfWeek x = DayOfWeek.of(date.getDay());
		LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

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

		Savepoint save = connect.setSavepoint();

		connect.setAutoCommit(false);

		try {

			stmt = connect.prepareStatement(
					"insert into ordine (indirizzo_consegna, numero_carta, prezzo_totale, note, stato, acquirente, email_acquirente, azienda, email_azienda, fattorino, email_fattorino) values "
							+ "(?,?,?,?,?,?,?,?,?,?,?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, order.getViaDiConsegna() + " " + order.getNumConsegna() + " " + order.getCittaConsegna()
					+ " " + order.getProvinciaConsegna());
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

	public List<Ordine_Bean> dammiOrdiniPreparazione(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"select Ordine.codice, Ordine.stato from Ordine where Ordine.stato=? and Ordine.email_azienda=?");
		stmt.setString(1, Ordine_Bean.IN_PREPARAZIONE);
		stmt.setString(2, azienda.getEmail());

		List<Ordine_Bean> listaOrdiniPreparazione = new ArrayList<Ordine_Bean>();

		ResultSet x = stmt.executeQuery();

		while (x.next()) {
			Ordine_Bean ordine = new Ordine_Bean(azienda, null, null, null);

			ordine.setStato(x.getString("in preparazione"));
			ordine.setAzienda(azienda);

			listaOrdiniPreparazione.add(ordine);
		}

		return listaOrdiniPreparazione;
	}

	public Ordine_Bean dammiOrdine(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select * from Ordine where Ordine.codice=?");
		stmt.setLong(1, idOrdine);

		ResultSet x = stmt.executeQuery();

		while (x.next()) {
			Ordine_Bean ordine = new Ordine_Bean(null, null, null, null);

			ordine.setStato("stato");

		}

		return null;
	}

	public List<Ordine_Bean> dammiConsegne(AccountFattorino_Bean fattorino) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"select Ordine.codice, Fattorino.email, Fattorino.nome from Fattorino, Ordine where Fattorino.email=?");
		stmt.setString(1, fattorino.getEmail());

		List<Ordine_Bean> listaConsegneFattorino = new ArrayList<Ordine_Bean>();

		ResultSet x = stmt.executeQuery();

		while (x.next()) {
			Ordine_Bean ordine = new Ordine_Bean(null, fattorino, null, null);

			ordine.setFattorino(fattorino);

			listaConsegneFattorino.add(ordine);
		}

		return listaConsegneFattorino;
	}

	public void ordineSetRitirato(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("update Ordine set Ordine.stato=?");
		stmt.setString(1, Ordine_Bean.RITIRATO);

		ResultSet x = stmt.executeQuery();

		return;
	}

	public void ordineSetConsegnato(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("update Ordine set Ordine.stato=?");
		stmt.setString(1, Ordine_Bean.CONSEGNATO);

		ResultSet x = stmt.executeQuery();

		return;
	}

}

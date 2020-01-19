package model.dao;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import exception.AziendaChiusaException;
import exception.FattorinoNonDisponibileException;
import interfaces.GestoreOrdineDao;
import interfaces.GestoreUtenteDAO;
import model.DBConnectionPool;
import model.ProdottoQuantita;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.Ordine_Bean;
import model.bean.Prodotto_Bean;

public class GestoreOrdineDAOImpl implements GestoreOrdineDao {

	private Connection connect;
	GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();

	// terminato
	/**
	 * Metodo che consente la creazione di Ordine a db.
	 * 
	 * @param order Ordine_Bean che contiene tutte le informazioni riguardante un
	 *              ordine
	 * @throws FattorinoNonDisponibileException
	 */
	public void creaOrdine(Ordine_Bean order)
			throws SQLException, AziendaChiusaException, FattorinoNonDisponibileException {
		if (order != null) {
			connect = DBConnectionPool.getConnection();
			DayOfWeek x;
			Date date = new Date();
			int day = date.getDay();
			if (day == 0)
				x = DayOfWeek.SUNDAY;
			else
				x = DayOfWeek.of(date.getDay());

			DayOfWeek precDay = x.minus(1);
			LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

			AccountAzienda_Bean az = order.getAzienda();

			// Checking if the company is open
			// I will not control same HH, because i'm checking if there's an error
			if (az.getOrarioDiApertura().isAfter(az.getOrarioDiChiusura())) {
				if (time.isAfter(az.getOrarioDiApertura()) && time.isAfter(az.getOrarioDiChiusura())) {
					// before midnight check
					if (!az.getGiorniDiApertura().contains(x))
						throw new AziendaChiusaException("L'azienda è chiusa oggi e non può evadere il tuo ordine");
				} else if (time.isBefore(az.getOrarioDiApertura()) && time.isBefore(az.getOrarioDiChiusura())) {
					// after midnight check
					if (!az.getGiorniDiApertura().contains(precDay))
						throw new AziendaChiusaException("L'azienda è chiusa oggi e non può evadere il tuo ordine");
				} else {
					// error case
					throw new AziendaChiusaException("L'azienda è chiusa oggi e non può evadere il tuo ordine");
				}
			} else if (time.isAfter(az.getOrarioDiApertura()) && time.isBefore(az.getOrarioDiChiusura())) {
				if (!az.getGiorniDiApertura().contains(x))
					throw new AziendaChiusaException("L'azienda è chiusa oggi e non può evadere il tuo ordine");
			}
			// Checking of the company completed

			// Taking all couriers of the selected city
			PreparedStatement preStmt = connect.prepareStatement(
					"select fattorino.email, fattorino.nome, fattorino.orario_inizio, fattorino.orario_fine "
							+ "from fattorino " + "where LOWER(fattorino.citta_consegna) = LOWER(?)");
			preStmt.setString(1, order.getAzienda().getCitta());
			ResultSet courierSet = preStmt.executeQuery();
			ArrayList<Quartet<String, String, LocalTime, LocalTime>> courierContainer = null;
			if (!courierSet.next()) {
				throw new FattorinoNonDisponibileException("Nessun Fattorino Disponibile");
			} else {
				courierContainer = new ArrayList<>();
				do {
					Time temp = (Time) courierSet.getObject("orario_inizio");
					Time temp2 = (Time) courierSet.getObject("orario_fine");
					courierContainer
							.add(new Quartet<String, String, LocalTime, LocalTime>(courierSet.getString("email"),
									courierSet.getString("nome"), temp.toLocalTime(), temp2.toLocalTime()));
				} while (courierSet.next());
			}
			// DECLARING THINGS
			// this list contains final values
			ArrayList<Pair<String, String>> listOfEmailFattorino = new ArrayList<Pair<String, String>>();
			// this PS contains a query to take days for a courier
			PreparedStatement hs = connect
					.prepareStatement("select giorniLavorativi.giorno " + "from giorniLavorativi " + "where email = ?");
			// Starting checks on HH
			/* Quartet structure: email, nome, orario_inizio, orario_fine */
			for (Quartet<String, String, LocalTime, LocalTime> tempRecord : courierContainer) {
				boolean timeCheckResult = false;
				boolean afterMidnight = false;
				/*
				 * * TIME CHECK
				 */
				if (tempRecord.getValue2().isAfter(tempRecord.getValue3())) {
					// Checking if endTime is smaller than startTime
					if (time.isAfter(tempRecord.getValue2()) && time.isAfter(tempRecord.getValue3())) {
						// Before midnight check
						timeCheckResult = true;
						afterMidnight = false;
					} else if (time.isBefore(tempRecord.getValue2()) && time.isBefore(tempRecord.getValue3())) {
						// After midnight check
						timeCheckResult = true;
						afterMidnight = true;
					}
				} else if (tempRecord.getValue2().compareTo(tempRecord.getValue3()) == 0) {
					// Company is always open (same HH)
					timeCheckResult = true;
					afterMidnight = false;
				} else if (time.isAfter(tempRecord.getValue2()) && time.isBefore(tempRecord.getValue3())) {
					// Standard check
					timeCheckResult = true;
					afterMidnight = false;
				}
				// Checking if preliminary control on the hour is ok
				if (timeCheckResult == true) {
					// taking days for the courier
					ArrayList<DayOfWeek> workDays = new ArrayList<DayOfWeek>();
					hs.setString(1, tempRecord.getValue0());
					ResultSet hsSet = hs.executeQuery();
					while (hsSet.next()) {
						String takenDay = (String) hsSet.getObject("giorno");
						if (takenDay.equalsIgnoreCase("Monday")) {
							workDays.add(DayOfWeek.MONDAY);
						} else if (takenDay.equalsIgnoreCase("Tuesday")) {
							workDays.add(DayOfWeek.TUESDAY);
						} else if (takenDay.equalsIgnoreCase("Wednesday")) {
							workDays.add(DayOfWeek.WEDNESDAY);
						} else if (takenDay.equalsIgnoreCase("Thursday")) {
							workDays.add(DayOfWeek.THURSDAY);
						} else if (takenDay.equalsIgnoreCase("Friday")) {
							workDays.add(DayOfWeek.FRIDAY);
						} else if (takenDay.equalsIgnoreCase("Saturday")) {
							workDays.add(DayOfWeek.SATURDAY);
						} else if (takenDay.equalsIgnoreCase("Sunday")) {
							workDays.add(DayOfWeek.SUNDAY);
						}
					}
					// checking work day
					if (afterMidnight == false) {
						if (workDays.contains(x)) {
							// OK!
							listOfEmailFattorino
									.add(new Pair<String, String>(tempRecord.getValue0(), tempRecord.getValue1()));
						}
					}
					if (afterMidnight == true) {
						if (workDays.contains(precDay)) {
							// OK
							listOfEmailFattorino
									.add(new Pair<String, String>(tempRecord.getValue0(), tempRecord.getValue1()));
						}
					}
				}
			}

			if (listOfEmailFattorino.isEmpty()) {
				throw new FattorinoNonDisponibileException("Nessun Fattorino Disponibile");
			}

			Random randomizer = new Random();
			Pair<String, String> fattorino = listOfEmailFattorino.get(randomizer.nextInt(listOfEmailFattorino.size()));

			connect.setAutoCommit(false);
			Savepoint save = connect.setSavepoint();

			System.out.println("TEST > " + fattorino.getValue0() + " - " + fattorino.getValue1());

			try {

				PreparedStatement stmt = connect.prepareStatement(
						"insert into ordine (indirizzo_consegna, numero_carta, prezzo_totale, note, stato, acquirente, email_acquirente, azienda, email_azienda, fattorino, email_fattorino,telefono_cliente) values "
								+ "(?,?,?,?,?,?,?,?,?,?,?,?)",
						java.sql.Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, order.getIndirizzoConsegna());
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
				stmt.setString(12, order.getTelefono());

				stmt.executeUpdate();
				Long idOrder = null;
				ResultSet setKeys = stmt.getGeneratedKeys();
				if (setKeys.next()) {
					idOrder = setKeys.getLong(1);
				}
				System.out.println(idOrder);
				order.setCodiceID(idOrder);
				for (ProdottoQuantita pq : order.getProdottiOrdinati()) {
					stmt = connect
							.prepareStatement("insert into prodottoquantita (quantita,prodotto,ordine) values (?,?,?)");
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

		if (idOrdine != null) {
			connect = DBConnectionPool.getConnection();

			PreparedStatement stmt = connect.prepareStatement("select * from Ordine where Ordine.codice=?");
			stmt.setLong(1, idOrdine);

			ResultSet x = stmt.executeQuery();

			if (x.next()) {
				return true;
			}

			return false;
		}

		return false;

	}

	// terminato
	/**
	 * Questo metodo restituisce gli ordini dell'azienda con all'interno solo i
	 * prodotti e le quantità. Non restituisce gli account associati.
	 */
	public List<Ordine_Bean> dammiOrdiniPreparazione(AccountAzienda_Bean azienda) throws SQLException {
		if (azienda != null) {
			connect = DBConnectionPool.getConnection();

			PreparedStatement stmt = connect
					.prepareStatement("select * from Ordine where email_azienda = ? and stato = ?");
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
				String telefono = x.getString("telefono_cliente");

				List<ProdottoQuantita> prodottiOrdinati = new ArrayList<ProdottoQuantita>();
				PreparedStatement stmt2 = connect.prepareStatement(
						"SELECT * FROM eatreorder.prodottoquantita join prodotto on prodotto.codice = prodottoquantita.prodotto and prodottoquantita.ordine = ?;");
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
				ordine.setTelefono(telefono);

				lista.add(ordine);
			}

			return lista;
		}
		return null;
	}

	// terminato
	public Ordine_Bean dammiOrdine(Long idOrdine) throws SQLException {

		if (idOrdine != null) {
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
				String telefono = x.getString("telefono_cliente");
				String emAzienda = x.getString("email_azienda");
				String emFattorino = x.getString("email_fattorino");
				String emCliente = x.getString("email_acquirente");

				GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();

				AccountAzienda_Bean azienda = (AccountAzienda_Bean) dao.dammiUtente(emAzienda);
				AccountFattorino_Bean fattorino = (AccountFattorino_Bean) dao.dammiUtente(emFattorino);
				AccountCliente_Bean cliente = (AccountCliente_Bean) dao.dammiUtente(emCliente);

				List<ProdottoQuantita> prodottiOrdinati = new ArrayList<ProdottoQuantita>();
				PreparedStatement stmt2 = connect.prepareStatement(
						"SELECT * FROM eatreorder.prodottoquantita join prodotto on prodotto.codice = prodottoquantita.prodotto and prodottoquantita.ordine = ?;");
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
				ordine.setTelefono(telefono);

				return ordine;
			}
		}
		return null;
	}

	// terminato
	/**
	 * Metodo che consente di avere la lista degli ordini da consegnare(in
	 * preparazione/ritirati). All'ordine sono associati tutti gli account ma non
	 * viene associato l'elenco dei prodotti contenuti.
	 */

	public List<Ordine_Bean> dammiConsegne(AccountFattorino_Bean fattorino) throws SQLException {

		if (fattorino != null) {
			connect = DBConnectionPool.getConnection();

			PreparedStatement stmt = connect
					.prepareStatement("select * from Ordine where email_fattorino = ? and (stato = ? or stato = ?)");
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
				String telefono = x.getString("telefono_cliente");
				String emAzienda = x.getString("email_azienda");

				String emCliente = x.getString("email_acquirente");

				AccountAzienda_Bean azienda = (AccountAzienda_Bean) dao.dammiUtente(emAzienda);
				AccountCliente_Bean cliente = (AccountCliente_Bean) dao.dammiUtente(emCliente);

				Ordine_Bean ordine = new Ordine_Bean(azienda, fattorino, cliente, null);
				ordine.setCodiceID(idOrdine);
				ordine.setCodiceCarta(codiceCarta);
				ordine.setIndirizzoConsegna(indirizzoConsegna);
				ordine.setNote(note);
				ordine.setPrezzoTotal(prezzoTotale);
				if (stato.equals(Ordine_Bean.CONSEGNATO)) {

					ordine.setStato(Ordine_Bean.RITIRATO);
					ordine.setStato(Ordine_Bean.CONSEGNATO);
				} else {
					ordine.setStato(stato);
				}
				ordine.setTelefono(telefono);

				lista.add(ordine);
			}

			return lista;
		}
		return null;
	}

	// terminato
	public void ordineSetRitirato(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("update Ordine set Ordine.stato=? where Ordine.codice=?");
		stmt.setString(1, Ordine_Bean.RITIRATO);
		stmt.setLong(2, idOrdine);
		stmt.executeUpdate();

		return;
	}

	// terminato

	public void ordineSetConsegnato(Long idOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("update Ordine set Ordine.stato=? where Ordine.codice=?");
		stmt.setString(1, Ordine_Bean.CONSEGNATO);
		stmt.setLong(2, idOrdine);

		stmt.executeUpdate();

		return;
	}

}

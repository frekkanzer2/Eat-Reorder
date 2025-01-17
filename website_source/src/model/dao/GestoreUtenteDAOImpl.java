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
import java.util.List;

import interfaces.GestoreUtenteDAO;
import model.DBConnectionPool;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountModeratore_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Ordine_Bean;
import model.bean.Prodotto_Bean;

public class GestoreUtenteDAOImpl implements GestoreUtenteDAO {

	private Connection connect;

	// terminato
	public boolean controlloEsistenzaMail(String email) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect
				.prepareStatement("select email from UtenteRegistrato where UtenteRegistrato.email=?");
		stmt.setString(1, email);

		ResultSet x = stmt.executeQuery();

		if (x.next()) {
			return true;
		}

		return false;

	}

	// terminato
	public void registrazioneCliente(AccountCliente_Bean cliente) throws SQLException {

		connect = DBConnectionPool.getConnection();
		connect.setAutoCommit(false);

		Savepoint save = connect.setSavepoint();

		try {
			PreparedStatement stmt = connect
					.prepareStatement("insert into utenteregistrato(email,pass,tipologia,is_banned) values (?,?,?,?)");
			stmt.setString(1, cliente.getEmail());
			stmt.setString(2, cliente.getPassword());
			stmt.setString(3, AccountUtenteRegistrato_Bean.Cliente);
			stmt.setBoolean(4, false);

			stmt.executeUpdate();

			stmt = connect.prepareStatement("insert into Cliente(nome, cognome, email) values (?,?,?)");

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCognome());
			stmt.setString(3, cliente.getEmail());
			stmt.executeUpdate();

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
	public void registrazioneAzienda(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();
		connect.setAutoCommit(false);

		Savepoint save = connect.setSavepoint();

		try {
			PreparedStatement stmt = connect
					.prepareStatement("insert into utenteregistrato(email,pass,tipologia,is_banned) values (?,?,?,?)");
			stmt.setString(1, azienda.getEmail());
			stmt.setString(2, azienda.getPassword());
			stmt.setString(3, AccountUtenteRegistrato_Bean.Azienda);
			stmt.setBoolean(4, false);

			stmt.executeUpdate();

			stmt = connect.prepareStatement(
					"insert into Azienda(nome, via, numero_civico, citta, provincia, partita_iva, telefono, orario_apertura, orario_chiusura, email) values (?,?,?,?,?,?,?,?,?,?)");

			stmt.setString(1, azienda.getNome());
			stmt.setString(2, azienda.getVia());
			stmt.setInt(3, azienda.getNumeroCivico());
			stmt.setString(4, azienda.getCitta());
			stmt.setString(5, azienda.getProvincia());
			stmt.setString(6, azienda.getPartitaIva());
			stmt.setString(7, azienda.getTelefono());
			stmt.setString(8, azienda.getOrarioDiApertura().truncatedTo(ChronoUnit.SECONDS).toString());
			stmt.setString(9, azienda.getOrarioDiChiusura().truncatedTo(ChronoUnit.SECONDS).toString());
			stmt.setString(10, azienda.getEmail());
			stmt.executeUpdate();

			for (DayOfWeek day : azienda.getGiorniDiApertura()) {
				stmt = connect.prepareStatement("insert into giornilavorativi (giorno,email) values (?,?)");
				stmt.setString(1, day.toString());
				stmt.setString(2, azienda.getEmail());
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
	public void registrazioneFattorino(AccountFattorino_Bean fattorino) throws SQLException {

		connect = DBConnectionPool.getConnection();
		connect.setAutoCommit(false);

		Savepoint save = connect.setSavepoint();

		try {
		PreparedStatement stmt = connect
				.prepareStatement("insert into utenteregistrato(email,pass,tipologia,is_banned) values (?,?,?,?)");
		stmt.setString(1, fattorino.getEmail());
		stmt.setString(2, fattorino.getPassword());
		stmt.setString(3, AccountUtenteRegistrato_Bean.Fattorino);
		stmt.setBoolean(4, false);

		stmt.executeUpdate();

		stmt = connect.prepareStatement(
				"insert into Fattorino(nome, cognome, telefono, citta_consegna, provincia , orario_inizio, orario_fine, email) values (?,?,?,?,?,?,?,?)");

		stmt.setString(1, fattorino.getNome());
		stmt.setString(2, fattorino.getCognome());
		stmt.setString(3, fattorino.getTelefono());
		stmt.setString(4, fattorino.getCittaConsegna());
		stmt.setString(5, fattorino.getProvinciaConsegna());
		stmt.setString(6, fattorino.getInizioConsegne().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(7, fattorino.getFineConsegne().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(8, fattorino.getEmail());
		stmt.executeUpdate();

		for (DayOfWeek day : fattorino.getGiorniDiConsegna()) {
			stmt = connect.prepareStatement("insert into giornilavorativi (giorno,email) values (?,?)");
			stmt.setString(1, day.toString());
			stmt.setString(2, fattorino.getEmail());
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
	public boolean controllaBan(String email) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select is_banned from utenteregistrato where email = ?");
		stmt.setString(1, email);

		ResultSet x = stmt.executeQuery();

		if (x.next()) {
			return x.getBoolean("is_banned");
		}

		return false;
	}

	// terminato
	public boolean controllaEsistenzaAccount(String user, String password) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"select * from UtenteRegistrato where UtenteRegistrato.email=? and UtenteRegistrato.pass=?");
		stmt.setString(1, user);
		stmt.setString(2, password);

		ResultSet x = stmt.executeQuery();

		if (x.next()) {
			return true;
		}

		return false;
	}

	//// TERMINATO
	/**
	 * Questo metodo restituisce l'oggetto AccountUtenteRegitrato che pu� essere uno
	 * dei 4 account disponibili. E' necessario effettuare il cast alla tipologia
	 * desiderata a seconda del tipo di oggetto restituito.
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public AccountUtenteRegistrato_Bean dammiUtente(String email) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select * from utenteregistrato where email= ?");

		stmt.setString(1, email);
		ResultSet set = stmt.executeQuery();

		if (set.next()) {

			// salvo la password da associare all'account
			String password = set.getString("pass");

			// controllo adesso il tipo di utente da creare

			// caso utente = cliente
			if (set.getString("tipologia").equals(AccountUtenteRegistrato_Bean.Cliente)) {

				PreparedStatement stmt2 = connect.prepareStatement("select * from cliente where email = ?");
				stmt2.setString(1, email);

				ResultSet res = stmt2.executeQuery();

				if (res.next()) {

					AccountCliente_Bean cliente = new AccountCliente_Bean(email, password, res.getString("nome"),
							res.getString("cognome"));
					return cliente;

				} else
					return null;

			}

			// caso utente = moderatore
			else if (set.getString("tipologia").equals(AccountUtenteRegistrato_Bean.Moderatore)) {

				PreparedStatement stmt2 = connect.prepareStatement("select * from moderatore where email = ?");
				stmt2.setString(1, email);

				ResultSet res = stmt2.executeQuery();

				if (res.next()) {

					AccountModeratore_Bean moderatore = new AccountModeratore_Bean(email, password, res.getLong("id"));
					return moderatore;
				} else
					return null;

			}

			// caso utente = fattorino
			else if (set.getString("tipologia").equals(AccountUtenteRegistrato_Bean.Fattorino)) {

				PreparedStatement stmt2 = connect.prepareStatement("select * from fattorino where email = ?");
				stmt2.setString(1, email);

				ResultSet res = stmt2.executeQuery();

				if (res.next()) {

					String nome = res.getString("nome");
					String cognome = res.getString("cognome");
					String telefono = res.getString("telefono");
					String cittaConsegna = res.getString("citta_consegna");
					String provinciaConsegna = res.getString("provincia");
					LocalTime inizioConsegne = LocalTime.parse(res.getString("orario_inizio"));
					LocalTime fineConsegne = LocalTime.parse(res.getString("orario_fine"));

					List<DayOfWeek> giorniDiConsegna = new ArrayList<DayOfWeek>();

					stmt2 = connect.prepareStatement("select giorno from giornilavorativi where email = ?");
					stmt2.setString(1, email);

					ResultSet x = stmt2.executeQuery();

					while (x.next()) {

						DayOfWeek day = DayOfWeek.valueOf(x.getString("giorno"));
						giorniDiConsegna.add(day);
					}

					AccountFattorino_Bean fattorino = new AccountFattorino_Bean(email, password, nome, cognome,
							telefono, cittaConsegna, provinciaConsegna, inizioConsegne, fineConsegne, giorniDiConsegna);
					return fattorino;
				} else
					return null;

			}

			// caso utente = Azienda
			else if (set.getString("tipologia").equals(AccountUtenteRegistrato_Bean.Azienda)) {

				PreparedStatement stmt2 = connect.prepareStatement("select * from azienda where email = ?");
				stmt2.setString(1, email);

				ResultSet res = stmt2.executeQuery();

				if (res.next()) {

					String nome = res.getString("nome");
					String via = res.getString("via");
					Integer numeroCivico = res.getInt("numero_civico");
					String telefono = res.getString("telefono");
					String citta = res.getString("citta");
					String provincia = res.getString("provincia");
					String partitaIVA = res.getString("partita_iva");
					LocalTime apertura = LocalTime.parse(res.getString("orario_apertura"));
					LocalTime chiusura = LocalTime.parse(res.getString("orario_chiusura"));

					List<DayOfWeek> giorniDiApertura = new ArrayList<DayOfWeek>();

					// ERROR HERE!!!
					stmt2 = connect.prepareStatement("select giorno from giornilavorativi where email = ?");
					stmt2.setString(1, email);

					ResultSet x = stmt2.executeQuery();

					while (x.next()) {

						DayOfWeek day = DayOfWeek.valueOf(x.getString("giorno"));
						giorniDiApertura.add(day);
					}

					AccountAzienda_Bean azienda = new AccountAzienda_Bean(email, password, nome, via, numeroCivico,
							citta, provincia, telefono, partitaIVA, apertura, chiusura, giorniDiApertura);

					List<Prodotto_Bean> prodotti = new ArrayList<Prodotto_Bean>();
					stmt2 = connect.prepareStatement("select * from prodotto where email = ?");
					stmt2.setString(1, email);
					ResultSet products = stmt2.executeQuery();

					while (products.next()) {

						Prodotto_Bean prod = new Prodotto_Bean();
						prod.setAzienda(azienda);
						prod.setCodice(products.getLong("codice"));
						prod.setDescrizione(products.getString("descrizione"));
						try {
							prod.setImmagine(new URL(products.getString("path_immagine")));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						prod.setNome(products.getString("nome"));
						prod.setPrezzo(products.getFloat("prezzo"));

						azienda.aggiungiProdotto(prod);

					}

					return azienda;

				} else
					return null;

			}

		}

		return null;
	}

	// Terminato
	public void aggiornaCliente(AccountCliente_Bean cliente) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"update Cliente JOIN UtenteRegistrato on Cliente.email=UtenteRegistrato.email set Cliente.nome=?, Cliente.cognome=?, UtenteRegistrato.pass=? where Cliente.email=? and UtenteRegistrato.email=?");
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getCognome());
		stmt.setString(3, cliente.getPassword());
		stmt.setString(4, cliente.getEmail());
		stmt.setString(5, cliente.getEmail());

		stmt.executeUpdate();

		return;
	}

	// terminato
	public void aggiornaAzienda(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();

		// aggiorno dati azienda a database
		PreparedStatement stmt = connect.prepareStatement(
				"update Azienda JOIN UtenteRegistrato on Azienda.email=UtenteRegistrato.email set Azienda.nome=?, Azienda.via=?, Azienda.numero_civico=?, Azienda.citta=?, Azienda.provincia=?, Azienda.partita_iva=?, Azienda.telefono=?, Azienda.orario_apertura=?, Azienda.orario_chiusura=?, UtenteRegistrato.pass=? where Azienda.email=? and UtenteRegistrato.email=?");
		stmt.setString(1, azienda.getNome());
		stmt.setString(2, azienda.getVia());
		stmt.setInt(3, azienda.getNumeroCivico());
		stmt.setString(4, azienda.getCitta());
		stmt.setString(5, azienda.getProvincia());
		stmt.setString(6, azienda.getPartitaIva());
		stmt.setString(7, azienda.getTelefono());
		stmt.setString(8, azienda.getOrarioDiApertura().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(9, azienda.getOrarioDiChiusura().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(10, azienda.getPassword());
		stmt.setString(11, azienda.getEmail());
		stmt.setString(12, azienda.getEmail());

		stmt.executeUpdate();

		// elimino i vecchi giorni lavorativi dell'azienda
		stmt = connect.prepareStatement("delete from giornilavorativi where email = ?");
		stmt.setString(1, azienda.getEmail());
		stmt.executeUpdate();

		// aggiungo i nuovi giorni lavorativi dell'azienda
		for (DayOfWeek day : azienda.getGiorniDiApertura()) {
			stmt = connect.prepareStatement("insert into giornilavorativi (email,giorno) values (?,?)");
			stmt.setString(1, azienda.getEmail());
			stmt.setString(2, day.toString());
			stmt.executeUpdate();
		}

	}

	// terminato
	public void aggiornaFattorino(AccountFattorino_Bean fattorino) throws SQLException {

		connect = DBConnectionPool.getConnection();

		// aggiorno i dati del fattorino
		PreparedStatement stmt = connect.prepareStatement(
				"update fattorino JOIN UtenteRegistrato on fattorino.email=UtenteRegistrato.email set UtenteRegistrato.pass=?,"
						+ "fattorino.nome =?,fattorino.cognome=?,fattorino.telefono=?, fattorino.citta_consegna=?,fattorino.provincia = ?,"
						+ " fattorino.orario_inizio=?,fattorino.orario_fine=? where fattorino.email=? and UtenteRegistrato.email=?");
		stmt.setString(1, fattorino.getPassword());
		stmt.setString(2, fattorino.getNome());
		stmt.setString(3, fattorino.getCognome());
		stmt.setString(4, fattorino.getTelefono());
		stmt.setString(5, fattorino.getCittaConsegna());
		stmt.setString(6, fattorino.getProvinciaConsegna());
		stmt.setString(7, fattorino.getInizioConsegne().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(8, fattorino.getFineConsegne().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(9, fattorino.getEmail());
		stmt.setString(10, fattorino.getEmail());

		stmt.executeUpdate();

		// elimino i vecchi giorni lavorativi del fattorino
		stmt = connect.prepareStatement("delete from giornilavorativi where email = ?");
		stmt.setString(1, fattorino.getEmail());
		stmt.executeUpdate();

		// aggiungo i nuovi giorni lavorativi dell'azienda
		stmt = connect.prepareStatement("insert into giornilavorativi (email,giorno) values (?,?)");
		for (DayOfWeek day : fattorino.getGiorniDiConsegna()) {
			stmt.setString(1, fattorino.getEmail());
			stmt.setString(2, day.toString());
			stmt.executeUpdate();
		}
	}

	// terminato
	public AccountAzienda_Bean dammiAziendaConOrdine(Long codiceOrdine) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select email_azienda from ordine where ordine.codice= ?");
		stmt.setLong(1, codiceOrdine);

		ResultSet set = stmt.executeQuery();

		if (set.next()) {

			PreparedStatement stmt2 = connect.prepareStatement("select * from azienda where email = ?");
			stmt2.setString(1, set.getString("email_azienda"));

			ResultSet res = stmt2.executeQuery();

			if (res.next()) {
				String email = res.getString("email");
				String nome = res.getString("nome");
				String via = res.getString("via");
				Integer numeroCivico = res.getInt("numero_civico");
				String telefono = res.getString("telefono");
				String citta = res.getString("citta");
				String provincia = res.getString("provincia");
				String partitaIVA = res.getString("partita_iva");
				LocalTime apertura = LocalTime.parse(res.getString("orario_apertura"));
				LocalTime chiusura = LocalTime.parse(res.getString("orario_chiusura"));

				List<DayOfWeek> giorniDiApertura = new ArrayList<DayOfWeek>();

				stmt2 = connect.prepareStatement("select giorno from giornilavorativi where email = ?");
				stmt2.setString(1, email);

				ResultSet x = stmt2.executeQuery();

				while (x.next()) {

					DayOfWeek day = DayOfWeek.valueOf(x.getString("giorno"));
					giorniDiApertura.add(day);
				}

				AccountAzienda_Bean azienda = new AccountAzienda_Bean(email, "", nome, via, numeroCivico, citta,
						provincia, telefono, partitaIVA, apertura, chiusura, giorniDiApertura);

				List<Prodotto_Bean> prodotti = new ArrayList<Prodotto_Bean>();
				stmt2 = connect.prepareStatement("select * from prodotto where email = ?");
				stmt2.setString(1, email);
				ResultSet products = stmt2.executeQuery();

				while (products.next()) {

					Prodotto_Bean prod = new Prodotto_Bean();
					prod.setAzienda(azienda);
					prod.setCodice(products.getLong("codice"));
					prod.setDescrizione(products.getString("descrizione"));
					try {
						prod.setImmagine(new URL(products.getString("path_immagine")));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					prod.setNome(products.getString("nome"));
					prod.setPrezzo(products.getFloat("prezzo"));

					azienda.aggiungiProdotto(prod);

				}

				return azienda;

			}

		}
		return null;

	}

	// terminato
	public List<AccountAzienda_Bean> dammiListaAziende(String citta) throws SQLException {

		connect = DBConnectionPool.getConnection();
		String newCitta= citta;
		
				
		PreparedStatement stmt = connect.prepareStatement("select * from Azienda where LOWER(Azienda.citta)=LOWER(?)");
		stmt.setString(1, newCitta);

		List<AccountAzienda_Bean> listaAziendeCitta = new ArrayList<AccountAzienda_Bean>();

		ResultSet res = stmt.executeQuery();

		while (res.next()) {

			String email = res.getString("email");
			String nome = res.getString("nome");
			String via = res.getString("via");
			Integer numeroCivico = res.getInt("numero_civico");
			String telefono = res.getString("telefono");
			String city = res.getString("citta");
			String provincia = res.getString("provincia");
			String partitaIVA = res.getString("partita_iva");
			LocalTime apertura = LocalTime.parse(res.getString("orario_apertura"));
			LocalTime chiusura = LocalTime.parse(res.getString("orario_chiusura"));

			List<DayOfWeek> giorniDiApertura = new ArrayList<DayOfWeek>();

			PreparedStatement stmt2 = connect.prepareStatement("select giorno from giornilavorativi where email = ?");
			stmt2.setString(1, email);

			ResultSet x = stmt2.executeQuery();

			while (x.next()) {

				DayOfWeek day = DayOfWeek.valueOf(x.getString("giorno"));
				giorniDiApertura.add(day);
			}

			AccountAzienda_Bean azienda = new AccountAzienda_Bean(email, "", nome, via, numeroCivico, city, provincia,
					telefono, partitaIVA, apertura, chiusura, giorniDiApertura);

			List<Prodotto_Bean> prodotti = new ArrayList<Prodotto_Bean>();
			stmt2 = connect.prepareStatement("select * from prodotto where email = ?");
			stmt2.setString(1,azienda.getEmail());
			ResultSet products = stmt2.executeQuery();

			while (products.next()) {

				Prodotto_Bean prod = new Prodotto_Bean();
				prod.setAzienda(azienda);
				prod.setCodice(products.getLong("codice"));
				prod.setDescrizione(products.getString("descrizione"));
				try {
					prod.setImmagine(new URL(products.getString("path_immagine")));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				prod.setNome(products.getString("nome"));
				prod.setPrezzo(products.getFloat("prezzo"));

				azienda.aggiungiProdotto(prod);

			}

			listaAziendeCitta.add(azienda);
		}

		return listaAziendeCitta;
	}

	// terminato
	public void banAzienda(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("update utenteregistrato set is_banned=1 where email= ?");
		stmt.setString(1, azienda.getEmail());

		stmt.executeUpdate();

		return;
	}

	// terminato
	public void aggiungiAlListino(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"insert into prodotto (nome,descrizione,prezzo,path_immagine,azienda,email) values(?,?,?,?,?,?)",
				java.sql.Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, prodotto.getNome());
		stmt.setString(2, prodotto.getDescrizione());
		stmt.setFloat(3, prodotto.getPrezzo());
		stmt.setString(4, prodotto.getImmagine().toString());
		stmt.setString(5, prodotto.getAzienda().getNome());
		stmt.setString(6, prodotto.getAzienda().getEmail());

		stmt.executeUpdate();
		ResultSet st = stmt.getGeneratedKeys();
		st.next();
		prodotto.setCodice(st.getLong(1));
		
	}

	// terminato
	public void aggiornaProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"update Prodotto set Prodotto.nome=?, Prodotto.descrizione=?, Prodotto.prezzo=?, Prodotto.path_immagine=? where Prodotto.email=? and prodotto.codice=?");
		stmt.setString(1, prodotto.getNome());
		stmt.setString(2, prodotto.getDescrizione());
		stmt.setFloat(3, prodotto.getPrezzo());
		stmt.setURL(4, prodotto.getImmagine());
		stmt.setString(5, prodotto.getAzienda().getEmail());
		stmt.setLong(6, prodotto.getCodice());

		stmt.executeUpdate();

		return;
	}

	// terminato
	public void rimuoviProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect
				.prepareStatement("delete from Prodotto where Prodotto.codice=? and Prodotto.email=?");
		stmt.setLong(1, prodotto.getCodice());
		stmt.setString(2, azienda.getEmail());

		stmt.executeUpdate();

		return;
	}
}

package model.DAO;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
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
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Prodotto_Bean;

public class GestoreUtenteDAOImpl {

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

		PreparedStatement stmt = connect
				.prepareStatement("insert into utenteregistrato(email,pass,tipologia,isbanned) values (?,?,?,?)");
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

		return;
	}

	// terminato
	public void registrazioneAzienda(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect
				.prepareStatement("insert into utenteregistrato(email,pass,tipologia,isbanned) values (?,?,?,?)");
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

	}

	// terminato
	public void registrazioneFattorino(AccountFattorino_Bean fattorino) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect
				.prepareStatement("insert into utenteregistrato(email,pass,tipologia,isbanned) values (?,?,?,?)");
		stmt.setString(1, fattorino.getEmail());
		stmt.setString(2, fattorino.getPassword());
		stmt.setString(3, AccountUtenteRegistrato_Bean.Fattorino);
		stmt.setBoolean(4, false);

		stmt.executeUpdate();

		stmt = connect.prepareStatement(
				"insert into Fattorino(nome, cognome, telefono, citta_consegna, orario_inizio, orario_fine, email) values (?,?,?,?,?,?,?)");

		stmt.setString(1, fattorino.getNome());
		stmt.setString(2, fattorino.getCognome());
		stmt.setString(3, fattorino.getTelefono());
		stmt.setString(4, fattorino.getCittaConsegna());
		stmt.setString(5, fattorino.getInizioConsegne().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(6, fattorino.getFineConsegne().truncatedTo(ChronoUnit.SECONDS).toString());
		stmt.setString(7, fattorino.getEmail());
		stmt.executeUpdate();

		for (DayOfWeek day : fattorino.getGiorniDiConsegna()) {
			stmt = connect.prepareStatement("insert into giornilavorativi (giorno,email) values (?,?)");
			stmt.setString(1, day.toString());
			stmt.setString(2, fattorino.getEmail());
			stmt.executeUpdate();
			
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
	 * Questo metodo restituisce l'oggetto AccountUtenteRegitrato che può essere uno
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

			// caso utente = fattorino
			if (set.getString("tipologia").equals(AccountUtenteRegistrato_Bean.Fattorino)) {

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
			if (set.getString("tipologia").equals(AccountUtenteRegistrato_Bean.Azienda)) {

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

	//// DA FINIRE
	public void aggiornaAzienda(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"update Azienda JOIN UtenteRegistrato on Azienda.email=UtenteRegistrato.email set Azienda.nome=?, Azienda.via=?, Azienda.numero_civico=?, Azienda.citta=?, Azienda.provincia=?, Azienda.partita_iva=?, Azienda.telefono=?, Azienda.orario_apertura=?, Azienda.orario_chiusura=?, UtenteRegistrato.pass=? where Azienda.email=? and UtenteRegistrato.email=?");
		stmt.setString(1, azienda.getNome());
		stmt.setString(2, azienda.getVia());
		stmt.setString(3, azienda.getNumeroCivico());
		stmt.setString(4, azienda.getCitta());
		stmt.setString(5, azienda.getProvincia());
		stmt.setString(6, azienda.getPartitaIva());
		stmt.setString(7, azienda.getTelefono());
		stmt.setTime(8, azienda.getOrarioDiApertura());
		stmt.setTime(9, azienda.getOrarioDiChiusura());
		stmt.setString(5, azienda.getProvincia());

		stmt.executeUpdate();

		return;
	}

	//// DA FINIRE
	public void aggiornaFattorino(AccountFattorino_Bean fattorino) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"update Azienda JOIN UtenteRegistrato on Azienda.email=UtenteRegistrato.email set Azienda.nome=?, Azienda.via=?, Azienda.numero_civico=?, Azienda.citta=?, Azienda.provincia=?, Azienda.partita_iva=?, Azienda.telefono=?, Azienda.orario_apertura=?, Azienda.orario_chiusura=?, UtenteRegistrato.pass=? where Azienda.email=? and UtenteRegistrato.email=?");
		stmt.setString(1, azienda.getNome());
		stmt.setString(2, azienda.getVia());
		stmt.setString(3, azienda.getNumeroCivico());
		stmt.setString(4, azienda.getCitta());
		stmt.setString(5, azienda.getProvincia());
		stmt.setString(6, azienda.getPartitaIva());
		stmt.setString(7, azienda.getTelefono());
		stmt.setTime(8, azienda.getOrarioDiApertura());
		stmt.setTime(9, azienda.getOrarioDiChiusura());
		stmt.setString(5, azienda.getProvincia());

		stmt.executeUpdate();

		return;
	}

	//// DA FINIRE
	public List<AccountAzienda_Bean> dammiListaAziende(String citta) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("select * from Azienda where Azienda.citta=?");
		stmt.setString(1, citta);

		List<AccountAzienda_Bean> listaAziendeCitta = new ArrayList<AccountAzienda_Bean>();

		ResultSet x = stmt.executeQuery();

		while (x.next()) {
			AccountAzienda_Bean azienda = new AccountAzienda_Bean();

			azienda.setCitta(citta);

			listaAziendeCitta.add(azienda);
		}

		return listaAziendeCitta;
	}

	public void banAzienda(AccountAzienda_Bean azienda) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement("delete from Azienda where Azienda.email=?");
		stmt.setString(1, azienda.getEmail());

		stmt.executeUpdate();

		return;
	}

	/// DA FINIRE
	public void aggiungiAlListino(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect
				.prepareStatement("select Prodotto.codice from Prodotto where Prodotto.codice=? and Prodotto.email=?");

		return;
	}

	public void aggiornaProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {

		connect = DBConnectionPool.getConnection();

		PreparedStatement stmt = connect.prepareStatement(
				"update Prodotto JOIN Azienda on Prodotto.email=Azienda.email set Prodotto.nome=?, Prodotto.descrizione=?, Prodotto.prezzo=?, Prodotto.path_immagine=? where Prodotto.email=? and Azienda.email=?");
		stmt.setString(1, prodotto.getNome());
		stmt.setString(2, prodotto.getDescrizione());
		stmt.setFloat(3, prodotto.getPrezzo());
		stmt.setURL(4, prodotto.getImmagine());
		stmt.setString(5, azienda.getEmail());
		stmt.setString(6, azienda.getEmail());

		stmt.executeUpdate();

		return;
	}

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

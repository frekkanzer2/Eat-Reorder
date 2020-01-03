package model.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnectionPool;


public class GestoreUtenteDAOImpl {

private Connection connect;
	
	public boolean controlloEsistenzaMail(String email) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("select * from UtenteRegistrato where UtenteRegistrato.email=?");
		stmt.setString(1, email);
		
		ResultSet x= stmt.executeQuery();
		
		if(x.next()) {
			return true;
		}
		
		return false;
		
	}
	
	
	public void registrazioneCliente(AccountCliente_Bean cliente) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("insert into Cliente(nome, cognome, email) values (?,?,?)");
		
		stmt.executeUpdate();
		
		return ;
	}
	
	
	public void registrazioneAzienda(AccountAzienda_Bean azienda) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("insert into Azienda(nome, via, numero_civico, citta, provincia, partita_iva, telefono, orario_apertura, orario_chiusura, email) values (?,?,?,?,?,?,?,?,?,?)");
		
		stmt.executeUpdate();
		
		return ;
	}
	
	
	public void registrazioneFattorino(AccountFattorino_Bean fattorino) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("insert into Fattorino(nome, cognome, telefono, citta_consegna, orario_inizio, orario_fine, email) values (?,?,?,?,?,?,?)");
		
		stmt.executeUpdate();
		
		return ;
	}
	
	public boolean controllaBan(String email) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("select Azienda.email from Azienda where Azienda.email=?");
		stmt.setString(1, email);
		
		ResultSet x= stmt.executeQuery();
		
		if(x.next()) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean controllaEsistenzaAccount(String user, String password) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("select * from UtenteRegistrato where UtenteRegistrato.email=? and UtenteRegistrato.password=?");
		stmt.setString(1, user);
		stmt.setString(2, password);
		
		ResultSet x= stmt.executeQuery();
		
		if(x.next()) {
			return true;
		}
		
		return false;
	}
	
	
	////DA FINIRE
	public AccountUtenteRegistrato_Bean dammiUtente(String email) {
		
		return null;
	}
	
	
	
	public void aggiornaCliente(AccountCliente_Bean cliente) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("update Cliente JOIN UtenteRegistrato on Cliente.email=UtenteRegistrato.email set Cliente.nome=?, Cliente.cognome=?, UtenteRegistrato.pass=? where Cliente.email=? and UtenteRegistrato.email=?");
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getCognome());
		stmt.setString(3, cliente.getPassword());
		stmt.setString(4, cliente.getEmail());
		stmt.setString(5, cliente.getEmail());
		
		stmt.executeUpdate();
		
		return;
	}
	
	
	////DA FINIRE
	public void aggiornaAzienda(AccountAzienda_Bean azienda) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("update Azienda JOIN UtenteRegistrato on Azienda.email=UtenteRegistrato.email set Azienda.nome=?, Azienda.via=?, Azienda.numero_civico=?, Azienda.citta=?, Azienda.provincia=?, Azienda.partita_iva=?, Azienda.telefono=?, Azienda.orario_apertura=?, Azienda.orario_chiusura=?, UtenteRegistrato.pass=? where Azienda.email=? and UtenteRegistrato.email=?");
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
	
	
	////DA FINIRE
	public void aggiornaFattorino(AccountFattorino_Bean fattorino) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("update Azienda JOIN UtenteRegistrato on Azienda.email=UtenteRegistrato.email set Azienda.nome=?, Azienda.via=?, Azienda.numero_civico=?, Azienda.citta=?, Azienda.provincia=?, Azienda.partita_iva=?, Azienda.telefono=?, Azienda.orario_apertura=?, Azienda.orario_chiusura=?, UtenteRegistrato.pass=? where Azienda.email=? and UtenteRegistrato.email=?");
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

	
	////DA FINIRE
	public List<AccountAzienda_Bean> dammiListaAziende(String citta) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("select * from Azienda where Azienda.citta=?");
		stmt.setString(1, citta);
		
		List<AccountAzienda_Bean> listaAziendeCitta = new ArrayList<AccountAzienda_Bean>();
		
		ResultSet x = stmt.executeQuery();
		
		while(x.next()) {
			AccountAzienda_Bean azienda= new AccountAzienda_Bean(    );
			
			azienda.setCitta(citta);
			
			listaAziendeCitta.add(azienda);
		}
		
		return listaAziendeCitta;
	}
	
	
	public void banAzienda(AccountAzienda_Bean azienda) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("delete from Azienda where Azienda.email=?");
		stmt.setString(1, azienda.getEmail());
		
		stmt.executeUpdate();
		
		return;
	}
	
	
	///DA FINIRE
	public void aggiungiAlListino(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("select Prodotto.codice from Prodotto where Prodotto.codice=? and Prodotto.email=?");
		
		
		return ;
	}
	
	
	public void aggiornaProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("update Prodotto JOIN Azienda on Prodotto.email=Azienda.email set Prodotto.nome=?, Prodotto.descrizione=?, Prodotto.prezzo=?, Prodotto.path_immagine=? where Prodotto.email=? and Azienda.email=?");
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
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("delete from Prodotto where Prodotto.codice=? and Prodotto.email=?");
		stmt.setLong(1, prodotto.getCodice());
		stmt.setString(2, azienda.getEmail());
		
		stmt.executeUpdate();
		
		return;
	}
}

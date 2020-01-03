package model.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
	public AccountUtenteRegistrato_Bean dammiUtente(String email) {
		
		return null;
	}
	
	
	public void aggiornaCliente(AccountCliente_Bean cliente) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("update Cliente, UtenteRegistrato set Cliente.nome=?, Cliente.cognome=?, UtenteRegistrato.password=?");
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getCognome());
		stmt.setString(3, cliente.getPassword());
		
		stmt.executeUpdate();
		
		return;
	}
}

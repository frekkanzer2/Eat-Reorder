package model.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import model.Carrello;

public class GestoreOrdineDAOImpl {
	
	private Connection connect;
	
	public Ordine_Bean creaOrdine(Ordine_Bean order, AccountCliente_Bean user, Carrello cart) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt = connect.prepareStatement("select Fattorino.email, Fattorino.nome, GiorniLavorativi.giorno, Fattorino.orario_inizio, Fattorino.orario_fine from Fattorino, GiorniLavorativi where Fattorino.citta_consegna='?' and GiorniLavorativi.giorno='?' and Fattorino.orario_inizio<'?' and Fattorino.orario_fine>'?';");
		
		ResultSet x= stmt.executeQuery();
		
		while(x.next()) {
			
		}
		
		return order;
	}
	
	public boolean controlloEsistenzaOrdine(Long idOrdine) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt = connect.prepareStatement("select * from Ordine where Ordine.codice='?';");
		stmt.setLong(1, idOrdine);
		
		ResultSet x= stmt.executeQuery();		
		
		if(x.next()) {
			return true;
		}
		
		return false;
	
	}
	
	public List<Ordine_Bean> dammiOrdiniPreparazione(AccountAzienda_Bean azienda) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt = connect.prepareStatement("select Ordine.codice, Ordine.stato from Ordine where Ordine.stato='?' and Ordine.email_azienda='?';");
		stmt.setString(1, Ordine_Bean.IN_PREPARAZIONE);
		stmt.setString(2, azienda.getEmail());
		
		
		List<Ordine_Bean> listaOrdiniPreparazione = new ArrayList<Ordine_Bean>();
		
		ResultSet x = stmt.executeQuery();
		
		while(x.next()) {
			Ordine_Bean ordine= new Ordine_Bean(azienda, null, null, null);
			
			ordine.setStato(x.getString("in preparazione"));
			ordine.setAzienda(azienda);
			
			listaOrdiniPreparazione.add(ordine);
		}
		
		return listaOrdiniPreparazione;	
	}
	
	
	public Ordine_Bean dammiOrdine(Long idOrdine) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("select * from Ordine where Ordine.codice='?';");
		stmt.setLong(1, idOrdine);
		
		ResultSet x= stmt.executeQuery();
			
		while(x.next()) {
			Ordine_Bean ordine= new Ordine_Bean(null, null, null, null); 
			
			ordine.setStato("stato");
					
		}
		
		return null;	
	}
	
	
	public List<Ordine_Bean> dammiConsegne(AccountFattorino_Bean fattorino) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt = connect.prepareStatement("select Ordine.codice, Fattorino.email, Fattorino.nome from Fattorino, Ordine where Fattorino.email='?';");
		stmt.setString(1, fattorino.getEmail());
		
		List<Ordine_Bean> listaConsegneFattorino = new ArrayList<Ordine_Bean>();
		
		ResultSet x = stmt.executeQuery();
		
		while(x.next()) {
			Ordine_Bean ordine= new Ordine_Bean(null, fattorino, null, null);
			
			ordine.setFattorino(fattorino);
		
			listaConsegneFattorino.add(ordine);
		}
		
		return listaConsegneFattorino;	
	}
	
	
	public void ordineSetRitirato(Long idOrdine) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("update Ordine set Ordine.stato='?';");
		stmt.setString(1, Ordine_Bean.RITIRATO);
		
		ResultSet x= stmt.executeQuery();
		
		return;
	}
	
	
	public void ordineSetConsegnato(Long idOrdine) throws SQLException {
		
		connect= DBConnectionPool.getConnection();
		
		PreparedStatement stmt= connect.prepareStatement("update Ordine set Ordine.stato='?';");
		stmt.setString(1, Ordine_Bean.CONSEGNATO);
		
		ResultSet x= stmt.executeQuery();
		
		return;
	}


}

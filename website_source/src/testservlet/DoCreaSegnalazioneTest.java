package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import interfaces.GestoreMail_Interface;
import interfaces.GestoreUtenteDAO;
import model.GestoreMail;
import model.bean.AccountCliente_Bean;
import model.dao.GestoreOrdineDAOImpl;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoCreaSegnalazione;

class DoCreaSegnalazioneTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoCreaSegnalazione servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		servlet= new DoCreaSegnalazione();
	}
	
	@Test
	public void CreaSegnalazioneAzienda_1() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("email", "password", "Mario", "Rossi");
		request.getSession().setAttribute("utente", cliente);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errmessage="Compila ogni campo";
		request.setParameter("id-order", "");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
		
	} 
	
	@Test
	public void CreaSegnalazioneAzienda_2() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("email", "password", "Mario", "Rossi");
		request.getSession().setAttribute("utente", cliente);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errmessage="Formato dei dati non valido";
		request.setParameter("id-order", "a");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	} 
	
	@Test
	public void CreaSegnalazioneAzienda_3() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("email", "password", "Mario", "Rossi");
		request.getSession().setAttribute("utente", cliente);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errmessage="L'ordine non esiste";
		request.setParameter("id-order", "333");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	} 
	
	@Test
	public void CreaSegnalazioneAzienda_4() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("email", "password", "Mario", "Rossi");
		request.getSession().setAttribute("utente", cliente);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errmessage="Formato dei dati non valido";
		request.setParameter("id-order", "5");
		request.setParameter("reason", "Problemi");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}
	
	@Test
	public void CreaSegnalazioneAzienda_5() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("email", "password", "Mario", "Rossi");
		request.getSession().setAttribute("utente", cliente);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errmessage="Formato dei dati non valido";
		request.setParameter("id-order", "5");
		request.setParameter("reason", "Problemiahusdbhaisjdhbajisdnaskndauhsdbausdbasaudbaisdhsai8dhas8duhsa8dsuahdihsabdishdbasihdbsaidbhsaidbshiadbshidbhisabdhsiabdhsiabdhsbiahusdbhaisjdhbajisdnaskndauhsdbausdbasaudbaisdhsai8dhas8duhsa8dsuahdihsabdishdbasihdbsaidbhsaidbshiadbshidbhisabdhsiabdhsiabdhsb");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}
	
	@Test
	public void CreaSegnalazioneAzienda_6() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("email", "password", "Mario", "Rossi");
		request.getSession().setAttribute("utente", cliente);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errmessage="Formato dei dati non valido";
		request.setParameter("id-order", "5");
		request.setParameter("reason", "Problemi con l’&%§ordine");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}
	
	@Test
	public void CreaSegnalazioneAzienda_7() throws Exception {
		Long IdOrdine= 5L;
		String descrizione="Problemi riguardo il pacco";
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("email", "password", "Mario", "Rossi");
		request.getSession().setAttribute("utente", cliente);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		GestoreMail mail= mock(GestoreMail.class);
		mail.inviaMailModeratore(IdOrdine, descrizione);
		servlet.setGestore(mail);
		
		String message="Email inviata correttamente!";
		request.setParameter("id-order", "5");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_confirm");
		assertEquals(message, attribute);
	}

}

package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.bean.AccountCliente_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoLogin;

class DoLoginTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoLogin servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		servlet= new DoLogin();
	}
	
	@Test
	public void Login_1() throws ServletException, IOException, SQLException {
		
		String email="Marco.dellobuono@libero.it";
		String password="Marcomar10";
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		when(dao.controllaEsistenzaAccount(email, password)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errorMessage = "I dati inseriti non sono corretti.";
		request.setParameter("email", "Marco.dello@");
		request.setParameter("password", "Marco");
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void Login_2() throws ServletException, IOException, SQLException {
		
		String email="Marco.dellobuono@libero.it";
		String password="Marcomar10";
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		when(dao.controllaEsistenzaAccount(email, password)).thenReturn(true);
		when(dao.controllaBan(email)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errorMessage = "L'utente " + email + " è bannato.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void Login_3() throws ServletException, IOException, SQLException {
		
		String email="Marco.dellobuono@libero.it";
		String password="Marcomar10";
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		when(dao.controlloEsistenzaMail(email)).thenReturn(true);
		when(dao.controllaEsistenzaAccount(email, password)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errorMessage = "I dati inseriti non sono corretti.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marco");
		servlet.doPost(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void Login_4() throws ServletException, IOException, SQLException {
		
		String email="Marco.dellobuono@libero.it";
		String password="Marcomar10";
		
		AccountUtenteRegistrato_Bean utente=new AccountCliente_Bean(email, password, "marco", "rossi");
				
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		when(dao.controllaEsistenzaAccount(email, password)).thenReturn(true);
		when(dao.dammiUtente(email)).thenReturn(utente);
		
		servlet.setGestore(dao);
		
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		servlet.doPost(request, response);
	}
	
	
	
	

	

}

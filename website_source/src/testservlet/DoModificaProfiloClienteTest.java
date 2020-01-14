package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.bean.AccountCliente_Bean;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoModificaProfiloCliente;


class DoModificaProfiloClienteTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoModificaProfiloCliente servlet;
	
	@BeforeEach
	public void SetUp() {
		servlet = new DoModificaProfiloCliente();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void ModificaProfiloCliente_1() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Ma");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("password", "Marcomar10");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_2() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "MaMaMaMaMaMaMaMaMaMaMaMaMaMaMaMaMaMaMaMaMaMa");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("password", "Marcomar10");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_3() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Marco&");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("password", "Marcomar10");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_4() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "De");
		request.setParameter("password", "Marcomar10");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_5() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "DelloBuonoDelloBuonoDelloBuonoDelloBuonoDelloBuono");
		request.setParameter("password", "Marcomar10");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_6() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono9");
		request.setParameter("password", "Marcomar10");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_7() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("password", "Marco");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_8() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("password", "Marcomar20Marcomar20Marcomar20");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_9() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("password", "Marco mar 10");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloCliente_10() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Marco";
		String cognome="Dello Buono";
		String password="Marcomar10";
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("Marco.dellobuono@libero.it", nome , cognome, password);
		request.getSession().setAttribute("utente", cliente);
		
		request.setParameter("nome", "MarcoMa");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("password", "Marcomar20");
		servlet.doGet(request, response);
				
		assertEquals(cliente.getNome(), "MarcoMa");
		assertEquals(cliente.getCognome(), "Dello Buono");
		assertEquals(cliente.getPassword(), "Marcomar20");
	}

	

}

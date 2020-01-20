package integrationTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.bean.AccountCliente_Bean;
import model.dao.GestoreUtenteDAOImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import servlets.DoRegistrazioneCliente;

public class DoRegistrazioneClienteTest extends Mockito {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoRegistrazioneCliente servlet;
	
	@BeforeEach
	public void SetUp() {
		servlet = new DoRegistrazioneCliente();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void RegistrazioneCliente_1() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_2() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "@m.dellobuono");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	
	@Test
	public void RegistrazioneCliente_3() throws ServletException, IOException, SQLException {
		
		String email = "Marco.dellobuono@libero.it";
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		when(dao.controlloEsistenzaMail(email)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errorMessage = "Email già presente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}

	@Test
	public void RegistrazioneCliente_4() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "mar");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_5() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10Marcomar10Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_6() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marco mar 10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_7() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "M");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_8() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "MarcoMarcoMarcoMarcoMarcoMarcoMarcoMarcoMarcoMarco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_9() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco853");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_10() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Db");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_11() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "DelloBuonoDelloBuonoDelloBuonoDelloBuonoDelloBuono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_12() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "DelloBuono853");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneCliente_13() throws ServletException, IOException, SQLException {
				
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String Message = "Registrazione avvenuta. Puoi loggare.";
		request.setParameter("email", "Marco.dellobuono@libero.it");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_confirm");
		assertEquals(Message, attribute);
	}

}

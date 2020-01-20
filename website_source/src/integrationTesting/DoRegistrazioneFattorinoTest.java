package integrationTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.dao.GestoreUtenteDAOImpl;
import servlets.DoRegistrazioneFattorino;

class DoRegistrazioneFattorinoTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoRegistrazioneFattorino servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new DoRegistrazioneFattorino();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void RegistrazioneFattorino_1() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_2() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "&");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_3() throws ServletException, IOException, SQLException {
		
		String email = "Francesco1@gmail.com";
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		when(dao.controlloEsistenzaMail(email)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errorMessage = "Email già presente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_4() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fatt");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_5() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattosjdjaodsijaijdoasd");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_6() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorin&&");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_7() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Fr");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_8() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "FrancescoFrancescoFrancescoFrancescoFrancesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_9() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesc&&");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_10() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "A");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_11() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "AbateAbateAbateAbateAbateAbateAbateAbateAbateAbateAbate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_12() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abat&");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_13() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "01");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_14() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "01234567891011");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_15() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "01234567a");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_16() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Sa");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_17() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "SalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_18() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salern00");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_19() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "S");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_20() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SASASA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_21() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "S0");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneFattorino_22() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String Message = "Registrazione avvenuta. Puoi loggare.";
		request.setParameter("email", "Francesco1@gmail.com");
		request.setParameter("password", "Fattorino");
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SA");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_confirm");
		assertEquals(Message, attribute);
	}

}

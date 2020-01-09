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

import model.dao.GestoreUtenteDAOImpl;
import servlets.DoRegistrazioneAzienda;


class DoRegistrazioneAziendaTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoRegistrazioneAzienda servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new DoRegistrazioneAzienda();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void RegistrazioneAzienda_1() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_2() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "@pizzapan.");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_3() throws ServletException, IOException, SQLException {
		String email = "pizzapanini@gmail.com";
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		when(dao.controlloEsistenzaMail(email)).thenReturn(true);
		servlet.setGestore(dao);
		
		String errorMessage = "Email già presente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_4() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "Pizza");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_5() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPizzaPizzaPizzaPizzaPizza");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_6() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "Pizza Pizza");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_7() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "P");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_8() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPaniniPizzaPaniniPizzaPaniniPizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_9() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "Pizza@");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_10() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Ro");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_11() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "RomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRoma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_12() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma821");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_13() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", " ");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_14() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "8291");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_15() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "8d2");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_16() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Mon");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_17() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "MontellaMontellaMontellaMontellaMontellaMontellaMontellaMontellaMontellaMontella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_18() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella821");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_19() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "A");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_20() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AVAVAVAV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_21() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "A2");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_22() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_23() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "32905327423290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_24() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "32b0a42c91");
		request.setParameter("iva", "07643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_25() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "0764");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_26() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "0764352056307643520563");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_27() throws ServletException, IOException {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "0764352056A");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void RegistrazioneAzienda_28() throws ServletException, IOException, SQLException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String Message = "Registrazione avvenuta. Puoi loggare.";
		request.setParameter("email", "pizzapanini@gmail.com");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "0764352056A");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_confirm");
		assertEquals(Message, attribute);
		
	}
	
	

}

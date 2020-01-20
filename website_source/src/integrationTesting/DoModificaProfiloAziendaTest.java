package integrationTesting;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
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

import model.bean.AccountAzienda_Bean;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoModificaProfiloAzienda;

class DoModificaProfiloAziendaTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoModificaProfiloAzienda servlet;
	
	@BeforeEach
	public void SetUp() {
		servlet = new DoModificaProfiloAzienda();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void ModificaProfiloAzienda_1() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Pi");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_2() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPaniniPizzaPaniniPizzaPaniniPizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_3() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Pizza&Panini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_4() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Ro");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_5() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "RomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRomaRoma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_6() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "RomaRoma&");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_7() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_8() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "13245");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_9() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "13&");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_10() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Sa");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_11() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "SalernoSalernoSalernoSalernoSalernoSalernoSalerno");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("iva", "07643520563");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_12() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Sale&rno");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_13() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "S");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_14() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "SASASA");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_15() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "S&");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_16() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "01234");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_17() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "012345678910012345678910");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_18() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "01234567a");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_19() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "Az");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_20() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1PizzaPan1PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_21() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1&");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloAzienda_22() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="PizzaPanini";
		String indirizzo="Roma";
		Integer civico=12;
		String citta="Montella";
		String provincia="AV";
		String telefono="3290532742";
		String password="PizzaPan1";
		LocalTime start_time=LocalTime.parse("12:00");
		LocalTime end_time=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean("pizzapanini@gmail.com", password, nome, indirizzo, civico, citta, provincia, telefono, "07643520563", start_time, end_time, checkbox);
		request.getSession().setAttribute("utente", azienda);
		
		request.setParameter("nome", "PizzaPanini");
		request.setParameter("indirizzo", "Roma");
		request.setParameter("civico", "12");
		request.setParameter("citta", "Montella");
		request.setParameter("provincia", "AV");
		request.setParameter("telefono", "3290532742");
		request.setParameter("password", "PizzaPan1");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
				
		assertEquals(azienda.getNome(), "PizzaPanini");
		assertEquals(azienda.getVia(), "Roma");
		assertEquals(azienda.getNumeroCivico(), 12);
		assertEquals(azienda.getCitta(), "Montella");
		assertEquals(azienda.getProvincia(), "AV");
		assertEquals(azienda.getTelefono(), "3290532742");
		assertEquals(azienda.getPassword(), "PizzaPan1");
		assertEquals(azienda.getOrarioDiApertura().toString(), "12:00");
		assertEquals(azienda.getOrarioDiChiusura().toString(), "15:00");
		assertEquals(azienda.getGiorniDiApertura().contains(DayOfWeek.MONDAY), true);
		
	}
	
	
	
	

}

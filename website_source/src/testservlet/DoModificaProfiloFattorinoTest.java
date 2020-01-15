package testservlet;

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
import model.bean.AccountFattorino_Bean;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoModificaProfiloFattorino;

class DoModificaProfiloFattorinoTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoModificaProfiloFattorino servlet;
	
	@BeforeEach
	public void SetUp() {
		servlet = new DoModificaProfiloFattorino();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void ModificaProfiloFattorino_1() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", password, nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Fr");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_2() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "FrancescoFrancescoFrancescoFrancescoFrancesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_3() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Franc&sco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_4() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "A");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_5() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "AbateAbateAbateAbateAbateAbateAbateAbateAbateAbate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_6() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abat&");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_7() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "0123");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_8() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678910");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_9() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "01234567a");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_10() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Sa");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_11() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "SalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalernoSalerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_12() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salern000");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_13() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "S");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_14() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Salerno");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_15() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "S%");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_16() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fa");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_17() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorinofattorinofattorinofattorinofattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_18() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino%%");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}
	
	@Test
	public void ModificaProfiloFattorino_19() throws ServletException, IOException {
		
		GestoreUtenteDAOImpl dao=mock(GestoreUtenteDAOImpl.class); 
		servlet.setGestore(dao);
		
		String nome="Francesco";
		String cognome="Abate";
		String numeroTelefono="012345678";
		String cittaConsegna="Salerno";
		String provinciaConsegna="Sa";
		String password="fattorino";
		LocalTime inizioConsegne=LocalTime.parse("12:00");
		LocalTime fineConsegne=LocalTime.parse("15:00");
		List<DayOfWeek> checkbox= new ArrayList<>();
		checkbox.add(DayOfWeek.MONDAY);
		checkbox.add(DayOfWeek.FRIDAY);
		checkbox.add(DayOfWeek.SATURDAY);
		
		AccountFattorino_Bean fattorino = new AccountFattorino_Bean("Francesco1@gmail.com", nome , cognome, numeroTelefono, cittaConsegna, provinciaConsegna, password, inizioConsegne, fineConsegne, checkbox);
		request.getSession().setAttribute("utente", fattorino);
		
		request.setParameter("nome", "Francesco");
		request.setParameter("cognome", "Abate");
		request.setParameter("telefono", "012345678");
		request.setParameter("citta", "Salerno");
		request.setParameter("provincia", "Sa");
		request.setParameter("password", "fattorino");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		
		assertEquals(fattorino.getNome(), "Francesco");
		assertEquals(fattorino.getCognome(), "Abate");
		assertEquals(fattorino.getTelefono(), "012345678");
		assertEquals(fattorino.getCittaConsegna(), "Salerno");
		assertEquals(fattorino.getProvinciaConsegna(), "Sa");
		assertEquals(fattorino.getPassword(), "fattorino");
		assertEquals(fattorino.getInizioConsegne().toString(), "12:00");
		assertEquals(fattorino.getFineConsegne().toString(), "15:00");
		assertEquals(fattorino.getGiorniDiConsegna().contains(DayOfWeek.MONDAY), true);	
	}
	
	
	

}

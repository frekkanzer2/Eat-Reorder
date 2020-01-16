package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.GestoreMail;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountModeratore_Bean;
import model.dao.GestoreOrdineDAOImpl;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoBannaAzienda;


class DoBannaAziendaTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoBannaAzienda servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		servlet= new DoBannaAzienda();
	}
	
	@Test
	public void GestisciSegnalazioneAzienda_1() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		//AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		AccountModeratore_Bean moderatore= new AccountModeratore_Bean("email_moderatore", "pass_moderatore", 5L);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		request.getSession().setAttribute("utente", moderatore);
					
		String errmessage="Compila ogni campo";
		request.setParameter("id-order", "");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void GestisciSegnalazioneAzienda_2() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		//AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		AccountModeratore_Bean moderatore= new AccountModeratore_Bean("email_moderatore", "pass_moderatore", 5L);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		request.getSession().setAttribute("utente", moderatore);
					
		String errmessage="L'ordine inserito non esiste";
		request.setParameter("id-order", "&");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void GestisciSegnalazioneAzienda_3() throws SQLException, ServletException, IOException {
		Long IdOrdine= 5L;
		
		//AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		AccountModeratore_Bean moderatore= new AccountModeratore_Bean("email_moderatore", "pass_moderatore", 5L);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		request.getSession().setAttribute("utente", moderatore);
					
		String errmessage="L'ordine inserito non esiste";
		request.setParameter("id-order", "555");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void GestisciSegnalazioneAzienda_4() throws SQLException, ServletException, IOException, MessagingException {
		Long IdOrdine= 5L;
		String email_azienda= "pizzapanini@gmail.com";
		String descrizioneban= "la tua azienda è stata bannata";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		AccountModeratore_Bean moderatore= new AccountModeratore_Bean("email_moderatore", "pass_moderatore", 5L);
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);
		when(dao.controlloEsistenzaOrdine(IdOrdine)).thenReturn(true);
		servlet.setGestore(dao);
		
		GestoreUtenteDAOImpl az= mock(GestoreUtenteDAOImpl.class);
		az.dammiAziendaConOrdine(IdOrdine);
		servlet.setGestore(az);
		
		GestoreUtenteDAOImpl ban= mock(GestoreUtenteDAOImpl.class);
		ban.banAzienda(azienda);
		servlet.setGestore(ban);
		
		GestoreMail mail= mock(GestoreMail.class);
		mail.inviaMailBan(azienda, descrizioneban);
		servlet.setGestore(mail);
		
		request.getSession().setAttribute("utente", moderatore);
					
		String message="Azienda bannata con successo!";
		request.setParameter("id-order", "5");
		request.setParameter("reason", "Problemi riguardo il pacco");
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_confirm");
		assertEquals(message, attribute);	
	} 
	
	
	

}

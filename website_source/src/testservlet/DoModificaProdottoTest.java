package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.bean.AccountAzienda_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoInserimentoProdotto;
import servlets.DoModificaProdotto;

class DoModificaProdottoTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoModificaProdotto servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		servlet= new DoModificaProdotto();
	}

	@Test
	public void ModificaProdotto_1() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void ModificaProdotto_2() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "Torta al miele con passata di zucchero bianco raro");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void ModificaProdotto_3() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "Torta al miele2");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void ModificaProdotto_4() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dol");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void ModificaProdotto_5() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolcealmielensaidhsauodhaudijbashdbjashkdbsaoudjsanodsjdoasdnoasdnosajdnsaojdnsjaondsjndsojadnjaosndoasdnjaosdajsdboadbjsabdhasidbaishdbaishbdaisdbihabdshaisbdodhaudijbashdbjashkdbsaoudjsanodsjdoasdnoasdnosajdnsaojdnsjaondsjndsojadnjaosndoasdnjaosdajsdboadbjsabdhasidbaishdbaishbdaisdbihabdshaisbd");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 

	@Test
	public void ModificaProdotto_6() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele &");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void ModificaProdotto_7() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "&");
		request.setParameter("img_path", "http://pathimmagine");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void ModificaProdotto_8() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		azienda.aggiungiProdotto(prodotto);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="I dati inseriti nella modifica del prodotto non sono corretti";
		request.setParameter("id", "10");
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "&");
		request.setParameter("img_path", "htt://");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	} 
	
	@Test
	public void ModificaProdotto_9() throws SQLException, ServletException, IOException {
		
		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();		
		prodotto.setCodice(15L);
		prodotto.setAzienda(azienda);
		
		request.getSession().setAttribute("utente", azienda);
					
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);
		dao.aggiornaProdotto(azienda, prodotto);
		servlet.setGestore(dao);
	
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		servlet.doGet(request,response);
	} 
}

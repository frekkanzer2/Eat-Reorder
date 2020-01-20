package integrationTesting;

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

import model.Carrello;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoModificaQuantita;

class DoModificaQuantitaTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoModificaQuantita servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		servlet= new DoModificaQuantita();
	}

	@Test
	public void ModificaQuantit‡Prodotto_1() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
		prodotto.setCodice(10L);
		
		//azienda.aggiungiProdotto(prodotto);
		//request.getSession().setAttribute("utente", azienda);
		
		request.getSession().setAttribute("utente", cliente);
			
		Carrello cart= new Carrello();

		cart.aggiungiProdotto(prodotto);
		request.getSession().setAttribute("carrello", cart);
		
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="La quantit‡ inserita non Ë corretta";
		request.setParameter("productId", "10");
		request.setParameter("productQuantity", "&");
		request.setParameter("companyMail", "pizzapanini@gmail.com");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	@Test
	public void ModificaQuantit‡Prodotto_2() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
		prodotto.setCodice(10L);
		
		request.getSession().setAttribute("utente", cliente);
			
		Carrello cart= new Carrello();

		cart.aggiungiProdotto(prodotto);
		request.getSession().setAttribute("carrello", cart);
		
		azienda.aggiungiProdotto(prodotto);
		
		GestoreUtenteDAOImpl dao= mock(GestoreUtenteDAOImpl.class);	
		when(dao.dammiUtente(email_azienda)).thenReturn(azienda);
		servlet.setGestore(dao);
		
		request.setParameter("productId", "10");
		request.setParameter("productQuantity", "12");
		request.setParameter("companyMail", "pizzapanini@gmail.com");
		
		servlet.doGet(request,response);
		
		assertEquals(cart.getProdotto(10L).getQta(), 12);
	}
}

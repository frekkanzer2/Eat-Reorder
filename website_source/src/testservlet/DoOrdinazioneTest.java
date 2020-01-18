package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.Carrello;
import model.GestoreMail;
import model.ProdottoQuantita;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.Ordine_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreOrdineDAOImpl;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoOrdinazione;

class DoOrdinazioneTest extends Mockito {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoOrdinazione servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		servlet= new DoOrdinazione();
	}

	@Test
	public void Ordinazione_1() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "");
		request.setParameter("notes", "aggiunta ketchup");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	
	@Test
	public void Ordinazione_2() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20Roma, 20Roma, 20Roma, 20Roma, 20");
		request.setParameter("notes", "aggiunta ketchup");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	
	@Test
	public void Ordinazione_3() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma&, 20");
		request.setParameter("notes", "aggiunta ketchup");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	
	@Test
	public void Ordinazione_4() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup");
		request.setParameter("credit-card", "3092");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	
	@Test
	public void Ordinazione_5() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup");
		request.setParameter("credit-card", "30929832103484233092983210348423");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	
	@Test
	public void Ordinazione_6() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup");
		request.setParameter("credit-card", "3092&983#103484*");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	
	@Test
	public void Ordinazione_7() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup aggiunta ketchup");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	
	@Test
	public void Ordinazione_8() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup&&&&");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	@Test
	public void Ordinazione_9() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup&&&&");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "3450");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	@Test
	public void Ordinazione_10() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup&&&&");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "34505349803450534980");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	@Test
	public void Ordinazione_11() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		String errmessage="Dati nell'ordine non corretti";
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup&&&&");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "345053498&");
		
		servlet.doGet(request,response);
		String attribute= (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);	
	}
	
	@Test
	public void Ordinazione_12() throws SQLException, ServletException, IOException {

		String email_azienda="pizzapanini@gmail.com";
		
		AccountAzienda_Bean azienda= new AccountAzienda_Bean(email_azienda, null , null, null , 0 , null, null, null, null, null, null, null);
		Prodotto_Bean prodotto= new Prodotto_Bean();
		prodotto.setCodice(10L);
		prodotto.setAzienda(azienda);
		prodotto.setNome("Torta al miele");
		prodotto.setDescrizione("Dolce al miele");
		prodotto.setPrezzo(0.70F);
		prodotto.setImmagine( new URL("http://pathimmagine"));
		
		Carrello cart= new Carrello();
		cart.aggiungiProdotto(prodotto);
		
		
		AccountCliente_Bean cliente= new AccountCliente_Bean("m.dellobuono@libero.it", "password", "Mario", "Rossi");
	
		
		request.getSession().setAttribute("utente", cliente);
		request.getSession().setAttribute("carrello", cart);
			
		
		GestoreOrdineDAOImpl dao= mock(GestoreOrdineDAOImpl.class);	
		servlet.setGestore(dao);
		
		GestoreMail mail= mock(GestoreMail.class);
		servlet.setGestore(mail);
	
		request.setParameter("address", "Roma, 20");
		request.setParameter("notes", "aggiunta ketchup");
		request.setParameter("credit-card", "3092983210348423");
		request.setParameter("telefono", "3450534980");
		
		servlet.doGet(request,response);
		Ordine_Bean order = (Ordine_Bean) request.getAttribute("watchingOrder");
		assertEquals(true, order!=null);
		
		
	}
}

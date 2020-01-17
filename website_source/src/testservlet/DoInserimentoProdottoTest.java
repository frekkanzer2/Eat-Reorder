package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import interfaces.GestoreUtenteDAO;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountModeratore_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreOrdineDAOImpl;
import model.dao.GestoreUtenteDAOImpl;
import servlets.DoInserimentoProdotto;

class DoInserimentoProdottoTest extends Mockito {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoInserimentoProdotto servlet;

	@BeforeEach
	void setUp() throws Exception {

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new DoInserimentoProdotto();
	}

	@Test
	public void InserisciProdotto_1() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_2() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Torta al miele con passata di zucchero bianco raro");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_3() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Torta al miele2");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_4() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dol");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_5() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione",
				"Dolcealmielensaidhsauodhaudijbashdbjashkdbsaoudjsanodsjdoasdnoasdnosajdnsaojdnsjaondsjndsojadnjaosndoasdnjaosdajsdboadbjsabdhasidbaishdbaishbdaisdbihabdshaisbdodhaudijbashdbjashkdbsaoudjsanodsjdoasdnoasdnosajdnsabsaoudjsanodsjdoasdnoasdnosajdnsabsaoudjsanodsjdoasdnoasdnosajdnsa");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_6() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele &");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_7() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "&");
		request.setParameter("img_path", "http://pathimmagine");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_8() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAOImpl dao = mock(GestoreUtenteDAOImpl.class);
		servlet.setGestore(dao);

		String errmessage = "Compilare tutti i campi correttamente.";
		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "htt://");

		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errmessage, attribute);
	}

	@Test
	public void InserisciProdotto_9() throws SQLException, ServletException, IOException {

		String email_azienda = "pizzapanini@gmail.com";

		AccountAzienda_Bean azienda = new AccountAzienda_Bean(email_azienda, null, null, null, 0, null, null, null,
				null, null, null, null);
		

		request.getSession().setAttribute("utente", azienda);

		GestoreUtenteDAO dao = new GestoreUtenteDAO() {

			@Override
			public boolean controlloEsistenzaMail(String email) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void registrazioneCliente(AccountCliente_Bean cliente) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public void registrazioneAzienda(AccountAzienda_Bean azienda) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public void registrazioneFattorino(AccountFattorino_Bean fattorino) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean controllaBan(String email) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean controllaEsistenzaAccount(String user, String password) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public AccountUtenteRegistrato_Bean dammiUtente(String email) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void aggiornaCliente(AccountCliente_Bean cliente) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public void aggiornaAzienda(AccountAzienda_Bean azienda) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public void aggiornaFattorino(AccountFattorino_Bean fattorino) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public AccountAzienda_Bean dammiAziendaConOrdine(Long codiceOrdine) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<AccountAzienda_Bean> dammiListaAziende(String citta) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void banAzienda(AccountAzienda_Bean azienda) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public void aggiungiAlListino(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {
				prodotto.setCodice(15L);

			}

			@Override
			public void aggiornaProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {
				// TODO Auto-generated method stub

			}

			@Override
			public void rimuoviProdotto(AccountAzienda_Bean azienda, Prodotto_Bean prodotto) throws SQLException {
				// TODO Auto-generated method stub

			}
		};

		servlet.setGestore(dao);

		request.setParameter("nome", "Torta al miele");
		request.setParameter("descrizione", "Dolce al miele");
		request.setParameter("costo", "0,70");
		request.setParameter("img_path", "http://pathimmagine");
		servlet.doGet(request, response);
		
		Prodotto_Bean prodotto = azienda.dammiProdotto(15L);
		assertEquals(prodotto.getCodice(), 15L);
		assertEquals(prodotto.getNome(), "Torta al miele");
		assertEquals(prodotto.getDescrizione(), "Dolce al miele");
		assertEquals(prodotto.getPrezzo(), 0.70F);
		assertEquals(prodotto.getImmagine().toString(), "http://pathimmagine");
	}

}

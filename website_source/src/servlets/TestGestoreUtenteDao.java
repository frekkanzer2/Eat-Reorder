package servlets;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.GestoreOrdineDao;
import model.Carrello;
import model.ProdottoQuantita;
import model.DAO.GestoreOrdineDAOImpl;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.Ordine_Bean;
import model.bean.Prodotto_Bean;

/**
 * Servlet implementation class TestGestoreUtenteDao
 */
@WebServlet("/TestGestoreUtenteDao")
public class TestGestoreUtenteDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestGestoreUtenteDao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("rox90@gmail.com", "palla", "Rosario", "Gagliardi");
		List<DayOfWeek> giorni = new ArrayList<DayOfWeek>();
		giorni.add(DayOfWeek.FRIDAY);
		giorni.add(DayOfWeek.SATURDAY);
 		AccountAzienda_Bean azienda = new AccountAzienda_Bean("azienda@gmail.com", "palla", "PizzaPanini", "via della valle", 23, "Caserta", "CE", "33422244", "02990938773", LocalTime.parse("12:00:00"), LocalTime.parse("15:00:00"), giorni);
 		Prodotto_Bean prod1 = new Prodotto_Bean();
 		prod1.setAzienda(azienda);
 		prod1.setCodice(233452L);
 		prod1.setDescrizione("prova prova prova");
 		prod1.setImmagine(new URL("http://ciao.com"));
 		prod1.setNome("Pane");
 		prod1.setPrezzo(3.5f);
 		
 		Prodotto_Bean prod2 = new Prodotto_Bean();
 		prod2.setAzienda(azienda);
 		prod2.setCodice(231452L);
 		prod2.setDescrizione("prova prova prova");
 		prod2.setImmagine(new URL("http://ciao.com"));
 		prod2.setNome("Pizza");
 		prod2.setPrezzo(4.5f);
 		
 		azienda.aggiungiProdotto(prod2);
 		azienda.aggiungiProdotto(prod1);
 		
 		Carrello cart = new Carrello();
 		cart.aggiungiProdotto(prod1);
 		cart.aggiungiProdotto(prod2);
 		
 		List<ProdottoQuantita> products = new ArrayList<>(cart.getProdotti());
 		
 		Ordine_Bean myOrder = new Ordine_Bean(azienda, null, cliente,products);
 		myOrder.setCittaConsegna("Caserta");
 		myOrder.setCodiceCarta("2453763873");
 		myOrder.setNote("prova pova prova");
 		myOrder.setNumConsegna(1);
 		myOrder.setPrezzoTotal(35f);
 		myOrder.setProvinciaConsegna("CE");
 		myOrder.setViaDiConsegna("via di pippo");
 		
		GestoreOrdineDao dao = new GestoreOrdineDAOImpl();
		try {
			dao.creaOrdine(myOrder);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

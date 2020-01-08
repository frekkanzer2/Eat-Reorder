package servlets;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.GestoreUtenteDAO;
import model.CheckFormato;
import model.bean.AccountAzienda_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoModificaProdotto
 */
@WebServlet("/DoModificaProdotto")
public class DoModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoModificaProdotto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// prendo l'account azienda dalla sessione
		AccountAzienda_Bean azienda = (AccountAzienda_Bean) request.getAttribute("utente");

		// se non esiste l'account azienda rimando alla pagina di login
		if (azienda == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		//prendo i parametri dalla richiesta
		Long id = Long.parseLong(request.getParameter("id"));
		String nome = request.getParameter("nome");
		Float prezzo = Float.parseFloat(request.getParameter("prezzo"));

		URL path = new URL(request.getParameter("img_path"));
		String descrizione = request.getParameter("descrizione");

		// creo un nuovo prodotto con i dati aggiornati
		Prodotto_Bean newProdotto = new Prodotto_Bean();
		newProdotto.setCodice(id);
		newProdotto.setNome(nome);
		newProdotto.setAzienda(azienda);
		newProdotto.setDescrizione(descrizione);
		newProdotto.setImmagine(path);
		newProdotto.setPrezzo(prezzo);
		if (CheckFormato.checkProdotto(nome, path, descrizione, prezzo)) {
			//prendo il prodotto con il codice id dal listino dell'azienda
			Prodotto_Bean prodInListino = azienda.dammiProdotto(id);

			try {
				
				dao.aggiornaProdotto(azienda, newProdotto);
			} catch (SQLException e) {

				System.err.println("ERROR DETECTED");
				e.printStackTrace();
				response.sendRedirect("ErrorPage.html");
				return;
			}

			prodInListino.modificaDati(newProdotto);
			request.getRequestDispatcher("Listino.jsp").forward(request, response);

		} else {
			// errore con il formato dei parametri
			String errmessage = ("Compilare tutti i campi correttamente.");
			// Redirection to an error page
			request.setAttribute("msg_error", errmessage);
			request.getRequestDispatcher("ModificaProdotto.jsp").forward(request, response);
		}
	}

}

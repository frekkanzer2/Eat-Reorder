package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.GestoreUtenteDAO;
import model.Carrello;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoAggiungiAlCarrello
 */
@WebServlet("/DoAggiungiAlCarrello")
public class DoAggiungiAlCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoAggiungiAlCarrello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		AccountCliente_Bean utente = null;
		
		try {
			utente = (AccountCliente_Bean) session.getAttribute("utente");
		} catch (Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}
		
		if (utente == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		Carrello cart = (Carrello) session.getAttribute("carrello");
		if (cart == null) {
			cart = new Carrello();
		}
		Long id = null;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
		}
		String emailString = request.getParameter("azienda");

		try {
			AccountAzienda_Bean azienda = (AccountAzienda_Bean) dao.dammiUtente(emailString);
			Prodotto_Bean prod = azienda.dammiProdotto(id);
			if (cart.checkAziendaCarrello(prod)) {
				if (!cart.checkInCarrello(prod)) {
					cart.aggiungiProdotto(prod);
				}
				session.setAttribute("carrello", cart);
				request.getRequestDispatcher("Carrello.jsp").forward(request, response);
			} else {
				String errmessage = ("Il prodotto appena selezionato &egrave; fornito da una azienda diversa da quella "
						+ "che fornisce i prodotti attualmente presenti nel carrello. Effettua l'ordine oppure elimina"
						+ " i prodotti attualmente presenti nel carrello");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("Carrello.jsp").forward(request, response);
				return;
			}

		} catch (Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

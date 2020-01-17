package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.GestoreUtenteDAO;
import model.Carrello;
import model.CheckFormato;
import model.ProdottoQuantita;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoModificaQuantita
 */
@WebServlet("/DoModificaQuantita")
public class DoModificaQuantita extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestoreUtenteDAO manager = new GestoreUtenteDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoModificaQuantita() {
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
		AccountCliente_Bean utente = (AccountCliente_Bean) session.getAttribute("utente");
		if (utente == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		Carrello cart = (Carrello) session.getAttribute("carrello");
		// TODO Auto-generated method stub
		try {
			Long idProduct = Long.parseLong(request.getParameter("productId"));
			Integer newQuantity = Integer.parseInt(request.getParameter("productQuantity"));
			String companyMail = request.getParameter("companyMail");
			if (!CheckFormato.checkQuantità(newQuantity)) {
				// The newer quantity is not correct
				String errmessage = ("La quantità inserita non è corretta");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("Carrello.jsp").forward(request, response);
				return;
			}
			AccountAzienda_Bean companyInstance = (AccountAzienda_Bean) manager.dammiUtente(companyMail);
			Prodotto_Bean askedProduct = companyInstance.dammiProdotto(idProduct);
			cart.aggiornaQtaCarrello(askedProduct, newQuantity);
			session.setAttribute("carrello", cart);
			request.getRequestDispatcher("Carrello.jsp").forward(request, response);
		} catch (Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
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

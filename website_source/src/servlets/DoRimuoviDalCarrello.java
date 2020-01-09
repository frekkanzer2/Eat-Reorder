package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.bean.Prodotto_Bean;

/**
 * Servlet implementation class DoRimuoviDalCarrello
 */
@WebServlet("/DoRimuoviDalCarrello")
public class DoRimuoviDalCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoRimuoviDalCarrello() {
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
		AccountUtenteRegistrato_Bean utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
		if (utente == null || !utente.getTipo().equals(AccountUtenteRegistrato_Bean.Cliente)) {
			response.sendRedirect("Login.jsp");
			return;
		}
		Carrello cart = (Carrello) session.getAttribute("carrello");
		if (cart == null) {
			cart = new Carrello();
		}
		Long id = Long.parseLong(request.getParameter("id"));
		Prodotto_Bean prodMock = new Prodotto_Bean();
		prodMock.setCodice(id);

		cart.rimuoviProdotto(prodMock);
		session.setAttribute("carrello", cart);
		request.getRequestDispatcher("Carrello.jsp").forward(request, response);

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

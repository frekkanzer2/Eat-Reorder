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
import model.bean.AccountAzienda_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoEliminaProdotto
 */
@WebServlet("/DoEliminaProdotto")
public class DoEliminaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoEliminaProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// prendo l'account azienda dalla sessione
		AccountAzienda_Bean azienda = (AccountAzienda_Bean) request.getAttribute("utente");

		// se non esiste l'account azienda rimando alla pagina di login
		if (azienda == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		Long idProdotto = Long.parseLong(request.getParameter("idProdotto"));
		
		Prodotto_Bean prodottoAzienda = azienda.dammiProdotto(idProdotto);
		try {
			
			dao.rimuoviProdotto(azienda, prodottoAzienda);
		} catch (SQLException e) {

			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}
		
		azienda.rimuoviProdotto(prodottoAzienda);
		request.getRequestDispatcher("Listino.jsp").forward(request, response);
		
		
		
	}

}

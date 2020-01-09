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
import model.bean.AccountUtenteRegistrato_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoVisualizzaListinoAzienda
 */
@WebServlet("/DoVisualizzaListinoAzienda")
public class DoVisualizzaListinoAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GestoreUtenteDAO dao = new GestoreUtenteDAOImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoVisualizzaListinoAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		AccountUtenteRegistrato_Bean user = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
		if (user != null) {
			if (user.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda)) {
				response.sendRedirect("HomepageAzienda.jsp");
				return;
			} else if (user.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino)) {
				response.sendRedirect("HomepageFattorino.jsp");
				return;
			} else if (user.getTipo().contentEquals(AccountUtenteRegistrato_Bean.Moderatore)) {
				response.sendRedirect("HomepageModeratore.jsp");
				return;
			}

		}
		
		String emailAzienda = request.getParameter("id");
		try {
			AccountAzienda_Bean listinoAzienda = (AccountAzienda_Bean) dao.dammiUtente(emailAzienda);
			request.setAttribute("azienda", listinoAzienda);
			request.getRequestDispatcher("AziendaScelta.jsp").forward(request,response);
		} catch (SQLException e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

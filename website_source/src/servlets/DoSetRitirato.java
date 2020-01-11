package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.GestoreOrdineDao;
import model.bean.AccountUtenteRegistrato_Bean;
import model.dao.GestoreOrdineDAOImpl;

/**
 * Servlet implementation class DoSetRitirato
 */
@WebServlet("/DoSetRitirato")
public class DoSetRitirato extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GestoreOrdineDao dao = new  GestoreOrdineDAOImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoSetRitirato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountUtenteRegistrato_Bean user = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
		if(user==null || !user.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino)){
			response.sendRedirect("Homepage.jsp");
			return;
		}
		
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			dao.ordineSetRitirato(id);
			request.getRequestDispatcher("DoVisualizzaConsegne").forward(request, response);
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

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
import model.bean.AccountFattorino_Bean;
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
		
		AccountFattorino_Bean user = null;
		
		try {
			user = (AccountFattorino_Bean) session.getAttribute("utente");
		} catch (Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}
		
		if(user==null){
			response.sendRedirect("Homepage.jsp");
			return;
		}
		
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			dao.ordineSetRitirato(id);
			request.getRequestDispatcher("DoVisualizzaConsegne").forward(request, response);
			return;
		} catch (SQLException e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
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

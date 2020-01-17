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
import model.bean.AccountAzienda_Bean;
import model.bean.Ordine_Bean;
import model.dao.GestoreOrdineDAOImpl;

/**
 * Servlet implementation class DoVaiVisualizzaOrdine
 */
@WebServlet("/DoVaiVisualizzaOrdine")
public class DoVaiVisualizzaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestoreOrdineDao manager = new GestoreOrdineDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoVaiVisualizzaOrdine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AccountAzienda_Bean azienda = (AccountAzienda_Bean) session.getAttribute("utente");
		
		if(azienda==null)
			response.sendRedirect("Login.jsp");

		Long id = Long.parseLong(request.getParameter("idOrdine"));
		Ordine_Bean order = null;
		try {
			order = manager.dammiOrdine(id);
		} catch (SQLException e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}
		request.setAttribute("ordineSelezionato", order);
		request.getRequestDispatcher("DettagliOrdineAzienda.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

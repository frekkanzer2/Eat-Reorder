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
import model.dao.GestoreUtenteDAOImpl;
import model.CheckFormato;
import model.bean.AccountCliente_Bean;

/**
 * Servlet implementation class DoModificaProfiloCliente
 */
@WebServlet("/DoModificaProfiloCliente")
public class DoModificaProfiloCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO utenteDao= new GestoreUtenteDAOImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoModificaProfiloCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting data from ModificaProfiloCliente.jsp
		HttpSession session = request.getSession();
		AccountCliente_Bean utenteLoggato= (AccountCliente_Bean)session.getAttribute("utente");
		
		if(utenteLoggato==null) {
			response.sendRedirect("Homepage.jsp");
			return;
		}
		
		String inputPassword = request.getParameter("password");
		String inputNome = request.getParameter("nome");
		String inputCognome = request.getParameter("cognome");
		AccountCliente_Bean newInformation=new AccountCliente_Bean(utenteLoggato.getEmail(),inputPassword,inputNome,inputCognome);
			try {
			//use CheckFormato for test the parameter
			if (CheckFormato.checkCliente(newInformation)) {
						//Confirm the changes
						utenteDao.aggiornaCliente(newInformation);
						utenteLoggato.modificaDati(newInformation);
						request.getRequestDispatcher("VisualizzaProfilo.jsp").forward(request, response);
					} else {
						// did not fill in all the fields
						String errmessage = ("Compilare tutti i campi correttamente.");
						// Redirection to an error page
						request.setAttribute("msg_error", errmessage);
						request.getRequestDispatcher("ModificaProfiloCliente.jsp").forward(request, response);
					}
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

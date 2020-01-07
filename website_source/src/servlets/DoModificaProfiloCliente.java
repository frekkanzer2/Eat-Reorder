package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.GestoreUtenteDAOImpl;
import model.CheckFormato;
import model.bean.AccountCliente_Bean;

/**
 * Servlet implementation class DoModificaProfiloCliente
 */
@WebServlet("/DoModificaProfiloCliente")
public class DoModificaProfiloCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
				AccountCliente_Bean utenteloggato = (AccountCliente_Bean)session.getAttribute("utente");
				String email=utenteloggato.getEmail();
				String input_password = request.getParameter("password");
				String input_nome = request.getParameter("nome");
				String input_cognome = request.getParameter("cognome");
				try {
					//use CheckFormato for test the parameter
					if (CheckFormato.formatoModificaCliente(input_nome, input_cognome, input_password)) {
						AccountCliente_Bean nuovo = new AccountCliente_Bean(email, input_password, input_nome, input_cognome);
						GestoreUtenteDAOImpl utente = new GestoreUtenteDAOImpl();
						utente.aggiornaCliente(nuovo);
						request.setAttribute("utente",nuovo);
						response.sendRedirect("Homepage.jsp");
					} else {
						// did not fill in all the fields
						String errmessage = ("Compilare tutti i campi correttamente.");
						// Redirection to an error page
						request.setAttribute("msg_error", errmessage);
						//DA CAMBIARE REDIRECT AL PROFILO CLIENTE
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

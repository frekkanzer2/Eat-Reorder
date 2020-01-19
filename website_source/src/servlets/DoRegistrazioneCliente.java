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
import model.CheckFormato;
import model.bean.AccountCliente_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoRegistrazioneCliente
 */
@WebServlet("/DoRegistrazioneCliente")
public class DoRegistrazioneCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO gestore = new GestoreUtenteDAOImpl();

	public void setGestore(GestoreUtenteDAO gestore) {
		this.gestore = gestore;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoRegistrazioneCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting data from RegistrazioneCliente.jsp
		HttpSession session = request.getSession();

		AccountUtenteRegistrato_Bean utenteLoggato = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
		// check if the user is logged or not
		// if is logged
		if (utenteLoggato != null) {
			response.sendRedirect("Homepage.jsp");
			return;
		}
		
		String input_email = request.getParameter("email");
		String input_password = request.getParameter("password");
		String input_nome = request.getParameter("nome");
		String input_cognome = request.getParameter("cognome");
		AccountCliente_Bean nuovo = new AccountCliente_Bean(input_email, input_password, input_nome, input_cognome);
		try {
			// use CheckFormato for test the parameter
			if (CheckFormato.checkCliente(nuovo)) {

				// Email already exists
				if (gestore.controlloEsistenzaMail(input_email)) {
					String errmessage = ("Email già presente.");
					request.setAttribute("msg_error", errmessage);
					request.getRequestDispatcher("RegistrazioneCliente.jsp").forward(request, response);
					return;
				} // create new client account
				else {

					gestore.registrazioneCliente(nuovo);
					String confirmMessage = ("Registrazione avvenuta. Puoi loggare.");
					// Confirm the registration
					request.setAttribute("msg_confirm", confirmMessage);
					request.getRequestDispatcher("Homepage.jsp").forward(request, response);
					return;
				}
			} else {
				// did not fill in all the fields
				String errmessage = ("Compilare tutti i campi correttamente.");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("RegistrazioneCliente.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
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

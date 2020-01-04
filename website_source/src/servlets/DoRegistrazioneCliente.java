package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.GestoreUtenteDAOImpl;
import model.bean.AccountCliente_Bean;
import model.bean.AccountUtenteRegistrato_Bean;

/**
 * Servlet implementation class DoRegistrazioneCliente
 */
@WebServlet("/DoRegistrazioneCliente")
public class DoRegistrazioneCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistrazioneCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Getting data from RegistrazioneCliente.jsp
		String input_email = request.getParameter("email");
		String input_password = request.getParameter("password");
		String input_nome = request.getParameter("nome");
		String input_cognome = request.getParameter("cognome");
		GestoreUtenteDAOImpl userManager = new GestoreUtenteDAOImpl();
		boolean isUserBanned = true;
		boolean doesUserExists = true;
		try {
			if (input_email != null) {
				// CASE USER ALREADY EXISTS (FALSE)
				String errmessage = ("L'email già esiste.");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("RegistrazioneCliente.jsp").forward(request, response);
			}
			// Checking if the user is banned
			isUserBanned = userManager.controllaBan(input_email);
			if (isUserBanned) {
				// CASE USER BANNED (TRUE)
				String errmessage = ("L'utente " + input_email + " è bannato.");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("RegistrazioneCliente.jsp").forward(request, response);
				return;
			}//CASE USER NOT BANNED (FALSE)
			//Checking if the user exists
			doesUserExists = userManager.controllaEsistenzaAccount(input_email, input_password);
			if (!doesUserExists) {
				//CASE USER NOT EXISTS (FALSE)
				String errmessage=("I dati inseriti non sono corretti.");
				//Redirection to an error page
				request.setAttribute("msg_error", errmessage);
	        	request.getRequestDispatcher("RegistrazioneCliente.jsp").forward(request, response);
			} // CASE USER NOT BANNED(FALSE)
			//CASE USER NOT EXISTS 
			//Checking if the data are correct
			else {
				if (!input_email.isEmpty()) {
					if (!(input_password.length() >= 7) || !(input_password.length() <= 20)) {
						if (!(input_nome.length() >= 3) || !(input_nome.length() <= 20)) {
							if (!(input_cognome.length() >= 3 || !(input_cognome.length() <= 20))) {
								AccountCliente_Bean nuovo = new AccountCliente_Bean(input_email, input_password,
										input_nome, input_cognome);
								GestoreUtenteDAOImpl utente = new GestoreUtenteDAOImpl();
								utente.registrazioneCliente(nuovo);
								HttpSession newSession = request.getSession();
								newSession.setAttribute("utente", nuovo);
								response.sendRedirect("HomepageCliente.jsp");
							}
						}
					}
				}
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

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
import model.Carrello;
import model.bean.AccountUtenteRegistrato_Bean;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO userManager = new GestoreUtenteDAOImpl();
	
	public DoLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Getting data from JSP Login.jsp
		String input_email = request.getParameter("email");
		String input_password = request.getParameter("password");
		
		boolean isUserBanned = true;
		boolean doesUserExists = true;
		try {
			if (input_email == null || input_password == null) {
				// CASE USER NOT EXISTS (FALSE)
				String errmessage = ("I dati inseriti non sono corretti.");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			// Checking if the user is banned
			isUserBanned = userManager.controllaBan(input_email);
			if (isUserBanned) {
				// CASE USER BANNED (TRUE)
				String errmessage = ("L'utente " + input_email + " è bannato.");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
				return;
			} else {
				// CASE USER NOT BANNED (FALSE)
				// Checking if the user exists
				doesUserExists = userManager.controllaEsistenzaAccount(input_email, input_password);
				if (!doesUserExists) {
					// CASE USER NOT EXISTS (FALSE)
					String errmessage = ("I dati inseriti non sono corretti.");
					// Redirection to an error page
					request.setAttribute("msg_error", errmessage);
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				} else {
					// CASE USER EXISTS (TRUE)
					AccountUtenteRegistrato_Bean loggedUser = userManager.dammiUtente(input_email);
					HttpSession newSession = request.getSession();
					newSession.setAttribute("utente", loggedUser);
					if (loggedUser.getTipo().contentEquals(AccountUtenteRegistrato_Bean.Cliente)) {
						Carrello cart = new Carrello();
						newSession.setAttribute("carrello", cart);
					}
					response.sendRedirect("Homepage.jsp");
					return;
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
		}

	}

}

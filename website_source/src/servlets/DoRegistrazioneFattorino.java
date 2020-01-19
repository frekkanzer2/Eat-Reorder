package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.GestoreUtenteDAO;
import model.CheckFormato;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoRegistrazioneFattorino
 */
@WebServlet("/DoRegistrazioneFattorino")
public class DoRegistrazioneFattorino extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO gestore = new GestoreUtenteDAOImpl();

	public void setGestore(GestoreUtenteDAO gestore) {
		this.gestore = gestore;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoRegistrazioneFattorino() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getting data from RegistrazioneFattorino.jsp

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
		String input_telefono = request.getParameter("telefono");
		String input_citta = request.getParameter("citta");
		String input_provincia = request.getParameter("provincia");
		LocalTime input_startime = LocalTime.parse(request.getParameter("start-time"));
		LocalTime input_endtime = LocalTime.parse(request.getParameter("end-time"));
		String[] day = request.getParameterValues("checkbox");
		List<DayOfWeek> giorni = new ArrayList<DayOfWeek>();
		if (day == null) {
			// did not fill in all the fields
			String errmessage = ("Inserire almeno un giorno lavorativo");
			// Redirection to an error page
			request.setAttribute("msg_error", errmessage);
			request.getRequestDispatcher("RegistrazioneAzienda.jsp").forward(request, response);
			return;
		}
		for (int i = 0; i < day.length; i++) {

			giorni.add(DayOfWeek.valueOf(day[i]));
		}

		AccountFattorino_Bean nuovo = new AccountFattorino_Bean(input_email, input_password, input_nome, input_cognome,
				input_telefono, input_citta, input_provincia, input_startime, input_endtime, giorni);
		try {
			// use CheckFormato for test the parameter
			if (CheckFormato.checkFattorino(nuovo)) {

				// Email already exists
				if (gestore.controlloEsistenzaMail(input_email)) {
					String errmessage = ("Email già presente.");
					request.setAttribute("msg_error", errmessage);
					request.getRequestDispatcher("RegistrazioneFattorino.jsp").forward(request, response);
					return;
				} // create new delivery man account
				else {

					gestore.registrazioneFattorino(nuovo);
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
				request.getRequestDispatcher("RegistrazioneFattorino.jsp").forward(request, response);
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

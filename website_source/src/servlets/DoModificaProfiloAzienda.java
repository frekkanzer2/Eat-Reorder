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
import model.bean.AccountAzienda_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoModificaProfiloAzienda
 */
@WebServlet("/DoModificaProfiloAzienda")
public class DoModificaProfiloAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO utenteDao = new GestoreUtenteDAOImpl();

	
	public void setGestore(GestoreUtenteDAO gestore) {
		this.utenteDao = gestore;
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoModificaProfiloAzienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Getting data from ModificaProfiloAzienda.jsp
		HttpSession session = request.getSession();
		AccountAzienda_Bean utenteLoggato = null;
		// check if the user is Company or not
		try {
			utenteLoggato = (AccountAzienda_Bean) session.getAttribute("utente");
		} // not Company get to Homepage
		catch (Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}

		if (utenteLoggato == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		String inputNome = request.getParameter("nome");
		String inputTelefono = request.getParameter("telefono");
		String inputIndirizzo = request.getParameter("indirizzo");

		int newCivico;
		try {
		String inputCivico=request.getParameter("civico");
		newCivico=Integer.parseInt(inputCivico);
		} catch(Exception e) {
			String errmessage=("Compilare tutti i campi correttamente.");
			//Redirection to an error page
			request.setAttribute("msg_error", errmessage);
		    request.getRequestDispatcher("ModificaProfiloAzienda.jsp").forward(request, response);
		    return;
		}
		
		String inputCitta = request.getParameter("citta");
		String inputProvincia = request.getParameter("provincia");
		LocalTime inputStarTime = LocalTime.parse(request.getParameter("start-time"));
		LocalTime inputEndTime = LocalTime.parse(request.getParameter("end-time"));
		String inputPassword = request.getParameter("password");
		String[] day = request.getParameterValues("checkbox");
		List<DayOfWeek> giorni = new ArrayList<DayOfWeek>();
		for (int i = 0; i < day.length; i++) {

			giorni.add(DayOfWeek.valueOf(day[i]));
		}

		AccountAzienda_Bean newInformation = new AccountAzienda_Bean(utenteLoggato.getEmail(), inputPassword, inputNome,
				inputIndirizzo, newCivico, inputCitta, inputProvincia, inputTelefono, utenteLoggato.getPartitaIva(),
				inputStarTime, inputEndTime, giorni);
		// if correct
		try {
			// use CheckFormato for test the parameter
			if (CheckFormato.checkAzienda(newInformation)) {

				// Confirm the changes
				utenteDao.aggiornaAzienda(newInformation);
				utenteLoggato.modificaDati(newInformation);
				request.getRequestDispatcher("VisualizzaProfilo.jsp").forward(request, response);
				return;
			} else {
				// did not fill in all the fields
				String errMessage = ("Compilare tutti i campi correttamente.");
				// Redirection to an error page
				request.setAttribute("msg_error", errMessage);
				request.getRequestDispatcher("ModificaProfiloAzienda.jsp").forward(request, response);
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

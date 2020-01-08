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
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoModificaProfiloFattorino
 */
@WebServlet("/DoModificaProfiloFattorino")
public class DoModificaProfiloFattorino extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO utenteDao= new GestoreUtenteDAOImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoModificaProfiloFattorino() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting data from ModificaProfiloFattorino.jsp
		HttpSession session = request.getSession();
		AccountFattorino_Bean utenteLoggato= null;
		//check if the user is Delivery or not
		try {
			utenteLoggato = (AccountFattorino_Bean)session.getAttribute("utente");
		}//not user get to login
		catch (ClassCastException e) {
				e.printStackTrace();
				response.sendRedirect("Homepage.jsp");
				return;
		}
		//if isn't logged 
		if(utenteLoggato==null) {
			response.sendRedirect("Homepage.jsp");
			return;
		}
		String inputNome = request.getParameter("nome");
		String inputCognome = request.getParameter("cognome");
		String inputTelefono=request.getParameter("telefono");
		String inputCitta=request.getParameter("citta");
		String inputProvincia=request.getParameter("provincia");
		String inputPassword = request.getParameter("password");
		LocalTime inputStarTime=LocalTime.parse(request.getParameter("start-time"));
		LocalTime inputEndTime=LocalTime.parse(request.getParameter("end-time"));
		String [] inputDay=request.getParameterValues("checkbox");
		List<DayOfWeek> giorni=new ArrayList<DayOfWeek>();
		for(int i=0;i<inputDay.length;i++) {
			String value=request.getParameter(inputDay[i]);
			if(value!=null)
				giorni.add(DayOfWeek.valueOf(value));
		}
		AccountFattorino_Bean newInformation= new AccountFattorino_Bean("",inputPassword,inputNome,inputCognome,inputTelefono,inputCitta, inputProvincia,inputStarTime,inputEndTime,giorni); 
		try {
			//use CheckFormato for test the parameter
			if(CheckFormato.checkFattorino(newInformation)) {
				//Confirm the changes
				utenteDao.aggiornaFattorino(newInformation);
				utenteLoggato.modificaDati(newInformation);
	        	request.getRequestDispatcher("VisualizzaProfilo.jsp").forward(request, response);
				}else{
					//did not fill in all the fields
					String errmessage=("Compilare tutti i campi correttamente.");
					//Redirection to an error page
					request.setAttribute("msg_error", errmessage);
		        	request.getRequestDispatcher("ModificaProfiloFattorino.jsp").forward(request, response);
				}
			}catch (SQLException e) {
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

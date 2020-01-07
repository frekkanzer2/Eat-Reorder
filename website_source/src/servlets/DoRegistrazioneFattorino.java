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

import model.CheckFormato;
import model.bean.AccountFattorino_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoRegistrazioneFattorino
 */
@WebServlet("/DoRegistrazioneFattorino")
public class DoRegistrazioneFattorino extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistrazioneFattorino() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting data from RegistrazioneFattorino.jsp
		String input_email = request.getParameter("email");
		String input_password = request.getParameter("password");
		String input_nome = request.getParameter("nome");
		String input_cognome = request.getParameter("cognome");
		String input_telefono=request.getParameter("telefono");
		String input_citta=request.getParameter("citta");
		String input_provincia=request.getParameter("provincia");
		LocalTime input_startime=LocalTime.parse(request.getParameter("start-time"));
		LocalTime input_endtime=LocalTime.parse(request.getParameter("end-time"));
		String [] day=request.getParameterValues("checkbox");
		List<DayOfWeek> giorni=new ArrayList<DayOfWeek>();
		for(int i=0;i<day.length;i++) {
			String value=request.getParameter(day[i]);
			if(value!=null)
				giorni.add(DayOfWeek.valueOf(value));
		}
		AccountFattorino_Bean nuovo=new AccountFattorino_Bean(input_email, input_password, input_nome, input_cognome, input_telefono, input_citta, input_provincia, input_startime, input_endtime, giorni);
		try {
			//use CheckFormato for test the parameter
			if(CheckFormato.checkFattorino(nuovo)) {
				GestoreUtenteDAOImpl gestore = new GestoreUtenteDAOImpl();
				// Email already exists
				if (gestore.controllaEsistenzaAccount(input_email,input_password)) {
					String errmessage = ("Email già presente.");
					request.setAttribute("msg_error", errmessage);
					request.getRequestDispatcher("RegistrazioneFattorino.jsp").forward(request, response);
				}//create new delivery man account
				else {
					GestoreUtenteDAOImpl utente=new GestoreUtenteDAOImpl();
					utente.registrazioneFattorino(nuovo);
					String confirmMessage=("Registrazione avvenuta. Puoi loggare.");
					//Confirm the registration
					request.setAttribute("msg_confirm", confirmMessage);
		        	request.getRequestDispatcher("Homepage.jsp").forward(request, response);
					
				}
				}else{
					//did not fill in all the fields
					String errmessage=("Compilare tutti i campi correttamente.");
					//Redirection to an error page
					request.setAttribute("msg_error", errmessage);
		        	request.getRequestDispatcher("RegistrazioneFattorino.jsp").forward(request, response);
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

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

import model.CheckFormato;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountFattorino_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoModificaProfiloFattorino
 */
@WebServlet("/DoModificaProfiloFattorino")
public class DoModificaProfiloFattorino extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		HttpSession session = request.getSession();
		AccountFattorino_Bean utenteloggato = (AccountFattorino_Bean)session.getAttribute("utente");
		String email=utenteloggato.getEmail();
		String input_nome = request.getParameter("nome");
		String input_cognome = request.getParameter("cognome");
		String input_telefono=request.getParameter("telefono");
		String input_citta=request.getParameter("citta");
		String input_provincia=request.getParameter("provincia");
		String input_password = request.getParameter("password");
		LocalTime input_startime=LocalTime.parse(request.getParameter("start-time"));
		LocalTime input_endtime=LocalTime.parse(request.getParameter("end-time"));
		String [] day=request.getParameterValues("checkbox");
		List<DayOfWeek> giorni=new ArrayList<DayOfWeek>();
		for(int i=0;i<day.length;i++) {
			String value=request.getParameter(day[i]);
			if(value!=null)
				giorni.add(DayOfWeek.valueOf(value));
		}
		try {
			//use CheckFormato for test the parameter
			if(CheckFormato.formatoRegistrazioneFattorino(email, input_password, input_nome, input_cognome, input_telefono, input_citta, input_provincia)) {
				System.err.println("1");
				GestoreUtenteDAOImpl utente = new GestoreUtenteDAOImpl();
				AccountFattorino_Bean nuovo=new AccountFattorino_Bean(email, input_password, input_nome, input_cognome, input_telefono, input_citta, input_provincia, input_startime, input_endtime, giorni);
				//Confirm the changes
				utente.aggiornaFattorino(nuovo);
	        	request.getRequestDispatcher("VisualizzaProfilo.jsp").forward(request, response);
				}else{
					System.err.println("2");
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

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
import model.bean.AccountCliente_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoModificaProfiloAzienda
 */
@WebServlet("/DoModificaProfiloAzienda")
public class DoModificaProfiloAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoModificaProfiloAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountAzienda_Bean utenteloggato = (AccountAzienda_Bean)session.getAttribute("utente");
		String email=utenteloggato.getEmail();
		String input_nome = request.getParameter("nome");
		String input_telefono=request.getParameter("telefono");
		String input_indirizzo=request.getParameter("indirizzo");
		String input_civico=request.getParameter("civico");
		int input_Civico=Integer.parseInt(input_civico);
		String input_citta=request.getParameter("citta");
		String input_provincia=request.getParameter("provincia");
		LocalTime input_startime=LocalTime.parse(request.getParameter("start_time"));
		LocalTime input_endtime=LocalTime.parse(request.getParameter("end_time"));
		String input_password = request.getParameter("password");
		String iva=utenteloggato.getPartitaIva();
		String [] day=request.getParameterValues("checkbox");
		List<DayOfWeek> giorni=new ArrayList<DayOfWeek>();
		for(int i=0;i<day.length;i++) {
			String value=request.getParameter(day[i]);
			if(value!=null)
				giorni.add(DayOfWeek.valueOf(value));
		}
		
		// if correct
		try {
			if (CheckFormato.formatoModificaAzienda(input_nome, input_indirizzo, input_Civico, input_citta, input_provincia, input_telefono, input_password)) {
					AccountAzienda_Bean nuovo=new AccountAzienda_Bean(email, input_password, input_nome, input_indirizzo, input_Civico, input_citta, input_provincia, input_telefono,iva, input_startime, input_endtime,giorni);
					GestoreUtenteDAOImpl utente= new GestoreUtenteDAOImpl();
					utente.aggiornaAzienda(nuovo);
					//Confirm the registration
		        	request.getRequestDispatcher("VisualizzaProfilo.jsp").forward(request, response);
				}else{
					//did not fill in all the fields
					String errmessage=("Compilare tutti i campi correttamente.");
					//Redirection to an error page
					request.setAttribute("msg_error", errmessage);
		        	request.getRequestDispatcher("ModificaProfiloAzienda.jsp").forward(request, response);
				}}catch (SQLException e) {
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

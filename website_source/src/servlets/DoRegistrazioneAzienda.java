package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckFormato;
import model.dao.GestoreUtenteDAOImpl;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;

/**
 * Servlet implementation class DoRegistrazioneAzienda
 */
@WebServlet("/DoRegistrazioneAzienda")
public class DoRegistrazioneAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistrazioneAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting data from RegistrazioneCliente.jsp
				String input_email = request.getParameter("email");
				String input_password = request.getParameter("password");
				String input_nome = request.getParameter("nome");
				String input_telefono=request.getParameter("telefono");
				String input_indirizzo=request.getParameter("indirizzo");
				String input_civico=request.getParameter("civico");
				int input_Civico=Integer.parseInt(input_civico);
				String input_citta=request.getParameter("citta");
				String input_provincia=request.getParameter("provincia");
				String input_iva=request.getParameter("iva");
				LocalTime input_startime=LocalTime.parse(request.getParameter("start-time"));
				LocalTime input_endtime=LocalTime.parse(request.getParameter("end-time"));
				String [] day=request.getParameterValues("checkbox");
				List<DayOfWeek> giorni=new ArrayList<DayOfWeek>();
				for(int i=0;i<day.length;i++) {
					String value=request.getParameter(day[i]);
					if(value!=null)
						giorni.add(DayOfWeek.valueOf(value));
				}
				
				// if correct
				try {
					if (CheckFormato.formatoRegistrazioneAzienda(input_email, input_password, input_nome, input_indirizzo,input_Civico, input_citta, input_provincia, input_telefono,input_iva)) {
						GestoreUtenteDAOImpl gestore = new GestoreUtenteDAOImpl();
						// Email already exists
						if (gestore.controllaEsistenzaAccount(input_email,input_password)) {
							String errmessage = ("Email già presente.");
							request.setAttribute("msg_error", errmessage);
							request.getRequestDispatcher("RegistrazioneAzienda.jsp").forward(request, response);
						}//create new account client 
						else {
							AccountAzienda_Bean newAccount = new AccountAzienda_Bean(input_email, input_password, input_nome, input_indirizzo,
									input_Civico, input_citta, input_provincia, input_telefono, input_iva, input_startime, input_endtime, giorni);
							GestoreUtenteDAOImpl userManager = new GestoreUtenteDAOImpl();
							userManager.registrazioneAzienda(newAccount);
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
				        	request.getRequestDispatcher("RegistrazioneAzienda.jsp").forward(request, response);
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

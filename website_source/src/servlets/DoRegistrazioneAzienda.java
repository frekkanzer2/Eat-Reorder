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
import model.dao.GestoreUtenteDAOImpl;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountUtenteRegistrato_Bean;

/**
 * Servlet implementation class DoRegistrazioneAzienda
 */
@WebServlet("/DoRegistrazioneAzienda")
public class DoRegistrazioneAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreUtenteDAO gestore = new GestoreUtenteDAOImpl();
       
	public void setGestore(GestoreUtenteDAO gestore) {
		this.gestore = gestore;
	}
	
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting data from RegistrazioneCliente.jsp
		HttpSession session = request.getSession();
		
		AccountUtenteRegistrato_Bean utenteLoggato=(AccountUtenteRegistrato_Bean)session.getAttribute("utente");
		//check if the user is logged or not
		//if is logged 
		if(utenteLoggato!=null) {
			response.sendRedirect("Homepage.jsp");
			return;
		}
		
		String inputEmail=request.getParameter("email");
		String inputPassword = request.getParameter("password");
		String inputNome = request.getParameter("nome");
		String inputTelefono=request.getParameter("telefono");
		String inputIndirizzo=request.getParameter("indirizzo");
		String inputCivico=request.getParameter("civico");
		int newCivico=Integer.parseInt(inputCivico);
		String inputCitta=request.getParameter("citta");
		String inputProvincia=request.getParameter("provincia");
		String inputIva=request.getParameter("iva");
		LocalTime inputStarTime=LocalTime.parse(request.getParameter("start-time"));
		LocalTime inputEndTime=LocalTime.parse(request.getParameter("end-time"));
		String [] inputDay=request.getParameterValues("checkbox");
		List<DayOfWeek> giorni=new ArrayList<DayOfWeek>();
		if (inputDay == null) {
			//did not fill in all the fields
			String errmessage=("Inserire almeno un giorno lavorativo");
			//Redirection to an error page
			request.setAttribute("msg_error", errmessage);
		    request.getRequestDispatcher("RegistrazioneAzienda.jsp").forward(request, response);
		}
		for(int i=0;i<inputDay.length;i++) {
			
				giorni.add(DayOfWeek.valueOf(inputDay[i]));
		}
		AccountAzienda_Bean newAccount = new AccountAzienda_Bean(inputEmail,inputPassword,inputNome,inputIndirizzo,newCivico,inputCitta,inputProvincia,inputTelefono,inputIva,inputStarTime,inputEndTime,giorni);
		try {
			//use CheckFormato for test the parameter
			if (CheckFormato.checkAzienda(newAccount)) {
				
				// Email already exists
				if (gestore.controlloEsistenzaMail(inputEmail)) {
					String errmessage = ("Email già presente.");
					request.setAttribute("msg_error", errmessage);
					request.getRequestDispatcher("RegistrazioneAzienda.jsp").forward(request, response);
				}//create new company account
				 else {
					
					gestore.registrazioneAzienda(newAccount);
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

package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.GestoreUtenteDAOImpl;
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
				String input_citta=request.getParameter("citta");
				String input_provincia=request.getParameter("provincia");
				String input_iva=request.getParameter("iva");
				
				// check if input are correct
				
				// if correct
				try {
					if () {
						GestoreUtenteDAOImpl gestore = new GestoreUtenteDAOImpl();
						// Email already exists
						if (gestore.controllaEsistenzaAccount(input_email,input_password)) {
							String errmessage = ("Email già presente.");
							request.setAttribute("msg_error", errmessage);
							request.getRequestDispatcher("RegistrazioneAzienda.jsp").forward(request, response);
						}//create new account client 
						else {
							AccountAzienda_Bean nuovo=new AccountAzienda_Bean(email, password, nome, via, numeroCivico, citta, provincia, telefono, partitaIva, orarioDiApertura, orarioDiChiusura, giorniDiApertura);
							GestoreUtenteDAOImpl utente = new GestoreUtenteDAOImpl();
							utente.registrazioneAzienda(nuovo);
							request.getSession(true).setAttribute("utente", nuovo);
							response.sendRedirect("Homepage.jsp");
						}}else{
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

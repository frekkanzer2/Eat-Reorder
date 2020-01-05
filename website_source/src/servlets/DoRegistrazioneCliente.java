package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.GestoreUtenteDAOImpl;
import model.bean.AccountCliente_Bean;
import model.bean.AccountUtenteRegistrato_Bean;

/**
 * Servlet implementation class DoRegistrazioneCliente
 */
@WebServlet("/DoRegistrazioneCliente")
public class DoRegistrazioneCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistrazioneCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Getting data from RegistrazioneCliente.jsp
		String input_email = request.getParameter("email");
		String input_password = request.getParameter("password");
		String input_nome = request.getParameter("nome");
		String input_cognome = request.getParameter("cognome");
		// check if input are correct
		boolean in_email = input_email.matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*@([a-zA-Z]+)\\.[a-zA-Z]+");
		boolean in_password = input_password.matches("[a-zA-Z0-9]{7,20}");
		boolean in_nome = input_nome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		boolean in_cognome = input_cognome.matches("[a-zA-Z ‘אטלעש]{3,20}");
		// if correct
		try {
			if (in_email == true && in_password == true && in_nome == true && in_cognome == true) {
				GestoreUtenteDAOImpl gestore = new GestoreUtenteDAOImpl();
				// Email already exists
				if (gestore.controlloEsistenzaMail(input_email)) {
					String errmessage = ("Email giא presente.");
					request.setAttribute("msg_error", errmessage);
					request.getRequestDispatcher("RegistrazioneCliente.jsp").forward(request, response);
				}//create new account client 
				else {
					AccountCliente_Bean nuovo = new AccountCliente_Bean(input_email, input_password, input_nome,
							input_cognome);
					GestoreUtenteDAOImpl utente = new GestoreUtenteDAOImpl();
					utente.registrazioneCliente(nuovo);
					request.getSession(true).setAttribute("utente", nuovo);
					response.sendRedirect("Homepage.jsp");
				}}else{
					//did not fill in all the fields
					String errmessage=("Compilare tutti i campi correttamente.");
					//Redirection to an error page
					request.setAttribute("msg_error", errmessage);
		        	request.getRequestDispatcher("RegistrazioneCliente.jsp").forward(request, response);
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

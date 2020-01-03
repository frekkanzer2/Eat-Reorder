package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AccountCliente_Bean;
import model.bean.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class RegistrazioneCliente
 */
@WebServlet("/RegistrazioneCliente")
public class RegistrazioneClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountCliente_Bean userLogged=(AccountCliente_Bean)request.getSession(false).getAttribute("cliente");
		if(userLogged!=null) {
			request.getSession(false).removeAttribute("utente");
		}
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		if(!email.isEmpty()&&!password.isEmpty()&&!nome.isEmpty()&&!cognome.isEmpty()) {
			AccountCliente_Bean utente=new AccountCliente_Bean(email,password,nome,cognome);
			GestoreUtenteDAOImpl utenteDao=new GestoreUtenteDAOImpl();
			try {
				utenteDao.registrazioneCliente(utente);
				request.getSession(true).setAttribute("utente",utente);
				response.sendRedirect("Homepage.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

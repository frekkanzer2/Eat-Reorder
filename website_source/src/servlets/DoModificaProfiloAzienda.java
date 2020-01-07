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
		// Getting data from ModificaProfiloAzienda.jsp
		HttpSession session = request.getSession();
		AccountAzienda_Bean utenteloggato = (AccountAzienda_Bean)session.getAttribute("utente");
		String input_nome = request.getParameter("nome");
		String input_telefono=request.getParameter("telefono");
		String input_indirizzo=request.getParameter("indirizzo");
		String input_civico=request.getParameter("civico");
		int input_Civico=Integer.parseInt(input_civico);
		String input_citta=request.getParameter("citta");
		String input_provincia=request.getParameter("provincia");
		LocalTime input_startime=LocalTime.parse(request.getParameter("start-time"));
		LocalTime input_endtime=LocalTime.parse(request.getParameter("end-time"));
		String input_password = request.getParameter("password");
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
					GestoreUtenteDAOImpl utente= new GestoreUtenteDAOImpl();
					utenteloggato.setNome(input_nome);
					utenteloggato.setVia(input_indirizzo);
					utenteloggato.setNumeroCivico(input_Civico);
					utenteloggato.setCitta(input_citta);
					utenteloggato.setProvincia(input_provincia);
					utenteloggato.setTelefono(input_telefono);
					utenteloggato.setPassword(input_password);
					utenteloggato.setOrarioDiApertura(input_startime);
					utenteloggato.setOrarioDiChiusura(input_endtime);
					utenteloggato.setGiorniDiApertura(giorni);
					//Confirm the changes
					utente.aggiornaAzienda(utenteloggato);
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

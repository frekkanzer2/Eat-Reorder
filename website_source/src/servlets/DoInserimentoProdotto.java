package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.sql.SQLException;

import model.CheckFormato;
import model.bean.AccountAzienda_Bean;
import model.bean.Prodotto_Bean;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoInserimentoProdotto
 */
@WebServlet("/DoInserimentoProdotto")
public class DoInserimentoProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoInserimentoProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting data from NuovoProdotto.jsp
		//pick fromthe session the Company 
		HttpSession session = request.getSession();
		AccountAzienda_Bean utenteloggato = (AccountAzienda_Bean)session.getAttribute("utente");
		String in_prodotto=request.getParameter("nome");
		String in_cost=request.getParameter("costo");
		float costo=Float.parseFloat(in_cost);
		String in_path=request.getParameter("img_path");
		URL path=new URL(in_path);
		String in_descr=request.getParameter("descrizione");
		try {
			//use CheckFormato for test the parameter
			if(CheckFormato.formatoInserisciProdotto(in_prodotto, in_descr, costo)) {
				//create new Product
				Prodotto_Bean nuovo=new Prodotto_Bean();
				nuovo.setAzienda(utenteloggato);
				nuovo.setNome(in_prodotto);
				nuovo.setPrezzo(costo);
				nuovo.setImmagine(path);
				nuovo.setDescrizione(in_descr);
				GestoreUtenteDAOImpl utente=new GestoreUtenteDAOImpl();
				//Confirm the update
				utente.aggiungiAlListino(utenteloggato, nuovo);
				request.getRequestDispatcher("Listino.jsp").forward(request, response);
			}else {
				//did not fill in all the fields
				String errmessage=("Compilare tutti i campi correttamente.");
				//Redirection to an error page
				request.setAttribute("msg_error", errmessage);
	        	request.getRequestDispatcher("NuovoProdotto.jsp").forward(request, response);
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

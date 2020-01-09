package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckFormato;
import model.GestoreMail;
import model.dao.GestoreOrdineDAOImpl;

/**
 * Servlet implementation class DoCreaSegnalazione
 */
@WebServlet("/DoCreaSegnalazione")
public class DoCreaSegnalazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoCreaSegnalazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Long orderId = null;
			String description = null;
			String tempOrderId = request.getParameter("id-order");
			if (!tempOrderId.equalsIgnoreCase(""))
				orderId = Long.parseLong(tempOrderId);
			description = request.getParameter("reason");
			if (orderId == null || description == null) {
				String errmessage=("Compila ogni campo");
				//Redirection to an error page
				request.setAttribute("msg_error", errmessage);
			    request.getRequestDispatcher("Segnalazione.jsp").forward(request, response);
			    return;
			}
			boolean validation = CheckFormato.checkSegnalazione(orderId, description);
			if (validation) {
				//Valid format (VALID)
				//Checking order existance
				GestoreOrdineDAOImpl manager = new GestoreOrdineDAOImpl();
				boolean existance = manager.controlloEsistenzaOrdine(orderId);
				if (!existance) {
					//Order doesn't exists (NOT VALID)
					String errmessage=("L'ordine non esiste");
					//Redirection to an error page
					request.setAttribute("msg_error", errmessage);
				    request.getRequestDispatcher("Segnalazione.jsp").forward(request, response);
				    return;
				} else {
					//Order already exists (VALID)
					GestoreMail mailManager = new GestoreMail();
					mailManager.inviaMailModeratore(orderId, description);
					//Redirect to the confirm message
					String confirmMessage=("Email inviata correttamente!");
					//Confirm the registration
					request.setAttribute("msg_confirm", confirmMessage);
				    request.getRequestDispatcher("Homepage.jsp").forward(request, response);
				    return;
				}
			} else {
				//Invalid format (NOT VALID)
				String errmessage=("Formato dei dati non valido");
				//Redirection to an error page
				request.setAttribute("msg_error", errmessage);
			    request.getRequestDispatcher("Segnalazione.jsp").forward(request, response);
			    return;
			}
		}catch(Exception e) {
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

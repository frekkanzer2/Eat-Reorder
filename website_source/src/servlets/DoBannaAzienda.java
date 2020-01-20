package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.GestoreMail_Interface;
import interfaces.GestoreOrdineDao;
import interfaces.GestoreUtenteDAO;
import model.GestoreMail;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountModeratore_Bean;
import model.bean.AccountUtenteRegistrato_Bean;
import model.dao.GestoreOrdineDAOImpl;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoBannaAzienda
 */
@WebServlet("/DoBannaAzienda")
public class DoBannaAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreOrdineDao orderManager = new GestoreOrdineDAOImpl();
	private GestoreUtenteDAO userManager = new GestoreUtenteDAOImpl();
	private GestoreMail_Interface mailManager = new GestoreMail();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoBannaAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AccountModeratore_Bean user = null;
		try {
			user = (AccountModeratore_Bean) session.getAttribute("utente");
		}catch(Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
		}
		if (user == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		try {
			Long orderId = null;
			String description = null;
			String tempOrderId = request.getParameter("id-order");
			if (!tempOrderId.equalsIgnoreCase("")) {
				try {
					orderId = Long.parseLong(tempOrderId);
				} catch (NumberFormatException e) {
					orderId = -1L;
				}
				description = request.getParameter("reason");
			}
			if (orderId == null || description == null) {
				String errmessage = ("Compila ogni campo");
				// Redirection to an error page
				request.setAttribute("msg_error", errmessage);
				request.getRequestDispatcher("HomepageModeratore.jsp").forward(request, response);
				return;
			}
		if (orderManager.controlloEsistenzaOrdine(orderId)) {
			String errmessage = null;
			//Order exists CORRECT CASE
			AccountAzienda_Bean company = userManager.dammiAziendaConOrdine(orderId);
			userManager.banAzienda(company);
			try {
				mailManager.inviaMailBan(company, description);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				errmessage = "Non è stato possibile inviare la mail che notifichi il ban";
				request.setAttribute("msg_error", errmessage);
			}
			String successMessage=("Azienda bannata con successo!");
			//Redirection to an error page
			request.setAttribute("msg_confirm", successMessage);
		    request.getRequestDispatcher("HomepageModeratore.jsp").forward(request, response);
		    return;
		} else {
			//Order does not exists, WRONG CASE
			String errmessage=("L'ordine inserito non esiste");
			//Redirection to an error page
			request.setAttribute("msg_error", errmessage);
		    request.getRequestDispatcher("HomepageModeratore.jsp").forward(request, response);
		    return;
		}
		}catch(SQLException e) {
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

	public void setGestore(GestoreOrdineDao dao) {
		this.orderManager = dao;
	}

	public void setGestore(GestoreMail mail) {
		this.mailManager=mail;
		
	}

	public void setGestore(GestoreUtenteDAO dao) {
		this.userManager=dao;
		
	}
	

}

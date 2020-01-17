package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.AziendaChiusaException;
import exception.FattorinoNonDisponibileException;
import interfaces.GestoreMail_Interface;
import interfaces.GestoreOrdineDao;
import interfaces.GestoreUtenteDAO;
import model.Carrello;
import model.CheckFormato;
import model.GestoreMail;
import model.ProdottoQuantita;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.Ordine_Bean;
import model.dao.GestoreOrdineDAOImpl;
import model.dao.GestoreUtenteDAOImpl;

/**
 * Servlet implementation class DoOrdinazione
 */
@WebServlet("/DoOrdinazione")
public class DoOrdinazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestoreMail_Interface mailManager = new GestoreMail();
	private GestoreOrdineDao manager = new GestoreOrdineDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoOrdinazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mailErrorMessage = null;
		HttpSession session = request.getSession();
		AccountCliente_Bean utenteloggato = (AccountCliente_Bean) session.getAttribute("utente");
		if (utenteloggato == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		AccountCliente_Bean user = (AccountCliente_Bean) utenteloggato;
		String address = request.getParameter("address");
		String notes = request.getParameter("notes");
		String creditCard = request.getParameter("credit-card");
		String telefono = request.getParameter("telefono");
		Carrello cart = (Carrello) session.getAttribute("carrello");
		if(cart==null || cart.isEmpty()) {
			
			response.sendRedirect("Carrello.jsp");
			return;
		}
		ArrayList<ProdottoQuantita> listOfProducts = new ArrayList<ProdottoQuantita>(cart.getProdotti());
		Ordine_Bean order = new Ordine_Bean(cart.getCurrentAzienda(), null, user, listOfProducts);
		order.setIndirizzoConsegna(address);
		order.setNote(notes);
		order.setCodiceCarta(creditCard);
		order.setTelefono(telefono);
		Float prezzoTotal = 0F;
		for (ProdottoQuantita pq: listOfProducts)
			prezzoTotal += (pq.getProdotto().getPrezzo()*pq.getQta());
		order.setPrezzoTotal(prezzoTotal);
		if (!CheckFormato.checkOrdine(order)) {
			String errmessage=("Dati nell'ordine non corretti");
			//Redirection to an error page
			request.setAttribute("msg_error", errmessage);
		    request.getRequestDispatcher("Ordinazione.jsp").forward(request, response);
		    return;
		}
		
		try {
			manager.creaOrdine(order);
		} catch (FattorinoNonDisponibileException e) {
			String errmessage=("Purtroppo non ci sono fattorini disponibili nella tua citt&agrave;. Riprova pi&ugrave; tardi o tra qualche giorno");
			//Redirection to an error page
			request.setAttribute("msg_error", errmessage);
		    request.getRequestDispatcher("Ordinazione.jsp").forward(request, response);
		    return;
		} catch (AziendaChiusaException e1) {
			String errmessage=("L'azienda &egrave; chiusa. Controlla i giorni e gli orari di apertura");
			//Redirection to an error page
			request.setAttribute("msg_error", errmessage);
		    request.getRequestDispatcher("Ordinazione.jsp").forward(request, response);
		    return;
		} catch (SQLException e2) {
			System.err.println("ERROR DETECTED");
			e2.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		} catch (Exception e3) {
			System.err.println("ERROR DETECTED");
			e3.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}
		cart.svuota();
		session.setAttribute("carrello", cart);
		try {
			mailManager.inviaMailOrdine(order);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			mailErrorMessage = ("Non &egrave; stato possibile inviare il riepilogo dell'ordine alla mail");
			//Redirection to an error page
			request.setAttribute("msg_error", mailErrorMessage);
		}
		//Confirm order creation
		
		request.setAttribute("watchingOrder", order);
	    request.getRequestDispatcher("DettagliOrdineCliente.jsp").forward(request, response);
	    return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void setGestore(GestoreOrdineDAOImpl dao) {
		this.manager=dao;
		
	}

	public void setGestore(GestoreMail mail) {
		this.mailManager=mail;
		
	}
}

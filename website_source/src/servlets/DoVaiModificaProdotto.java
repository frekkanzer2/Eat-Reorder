package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.AccountAzienda_Bean;
import model.bean.Prodotto_Bean;

/**
 * Servlet implementation class DoVaiModificaProdotto
 */
@WebServlet("/DoVaiModificaProdotto")
public class DoVaiModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoVaiModificaProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		AccountAzienda_Bean azienda = null;
		try {
			azienda = (AccountAzienda_Bean) session.getAttribute("utente");
		} catch(Exception e) {
			System.err.println("ERROR DETECTED");
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}
		if(azienda==null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		
		Long id = Long.parseLong(request.getParameter("idProdotto"));
		Prodotto_Bean prodotto = azienda.dammiProdotto(id);
		
		request.setAttribute("piattoSelezionato", prodotto);
		request.getRequestDispatcher("ModificaProdotto.jsp").forward(request, response);
		return;
		
	}

}

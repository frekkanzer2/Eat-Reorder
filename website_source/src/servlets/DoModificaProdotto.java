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
 * Servlet implementation class DoModificaProdotto
 */
@WebServlet("/DoModificaProdotto")
public class DoModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoModificaProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		AccountAzienda_Bean azienda = (AccountAzienda_Bean) request.getAttribute("utente");
		
		if(azienda==null)
			response.sendRedirect("Login.jsp");
		
		Long id = Long.parseLong(request.getParameter("idProdotto"));
		Prodotto_Bean prodotto = azienda.dammiProdotto(id);
		
		request.setAttribute("prodotto", prodotto);
		request.getRequestDispatcher("ModificaProdotto.jsp").forward(request, response);
				
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

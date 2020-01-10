package testservlet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import servlets.DoRegistrazioneFattorino;

class DoRegistrazioneFattorinoTest extends Mockito{

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private DoRegistrazioneFattorino servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		servlet = new DoRegistrazioneFattorino();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void RegistrazioneFattorino_1() {
		String errorMessage = "Compilare tutti i campi correttamente.";
		request.setParameter("email", "");
		request.setParameter("password", "Marcomar10");
		request.setParameter("nome", "Marco");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("cognome", "Dello Buono");
		request.setParameter("start-time", "12:00");
		request.setParameter("end-time", "15:00");
		request.setParameter("checkbox", "MONDAY");
		servlet.doGet(request, response);
		String attribute = (String) request.getAttribute("msg_error");
		assertEquals(errorMessage, attribute);
	}

}

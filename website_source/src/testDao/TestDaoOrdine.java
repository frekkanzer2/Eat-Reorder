package testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import exception.AziendaChiusaException;
import exception.FattorinoNonDisponibileException;
import model.ProdottoQuantita;
import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.Ordine_Bean;
import model.bean.Prodotto_Bean;

class TestDaoOrdine extends Mockito {
	Connection conn;

	@BeforeEach
	void setUpConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eatreordertesting?serverTimezone="
				+ TimeZone.getDefault().getID() + "&useSSL=false&allowPublicKeyRetrieval=true", "root", "password");
	}

	@AfterEach
	void dismissConnection() throws SQLException {
		conn.close();
	}

	@Test

	public void TestCreaOrdine() throws SQLException, AziendaChiusaException, FattorinoNonDisponibileException {

		GestoreUtenteDAOImpl us = mock(GestoreUtenteDAOImpl.class);
		GestoreOrdineDAOImpl dao = new GestoreOrdineDAOImpl(conn, us);
		AccountAzienda_Bean azienda = mock(AccountAzienda_Bean.class);
		when(azienda.getNome()).thenReturn("da Vincenzo");
		when(azienda.getEmail()).thenReturn("azienda1@gmail.com");
		when(azienda.getCitta()).thenReturn("salerno");
		when(azienda.getOrarioDiApertura()).thenReturn(LocalTime.parse("12:00"));
		when(azienda.getOrarioDiChiusura()).thenReturn(LocalTime.parse("02:00"));
		Set<DayOfWeek> array = new HashSet<DayOfWeek>();
		array.add(DayOfWeek.SATURDAY);
		array.add(DayOfWeek.SUNDAY);
		array.add(DayOfWeek.MONDAY);
		array.add(DayOfWeek.TUESDAY);
		array.add(DayOfWeek.WEDNESDAY);
		array.add(DayOfWeek.THURSDAY);
		array.add(DayOfWeek.FRIDAY);
		when(azienda.getGiorniDiApertura()).thenReturn(array);
		AccountCliente_Bean cliente = mock(AccountCliente_Bean.class);
		when(cliente.getNome()).thenReturn("Rosario");
		when(cliente.getEmail()).thenReturn("rosariogagliardi@msn.com");

		Prodotto_Bean x = mock(Prodotto_Bean.class);
		when(x.getCodice()).thenReturn(1L);
		
		List<ProdottoQuantita> list = new ArrayList<>();
		ProdottoQuantita c  = new ProdottoQuantita();
		c.setProdotto(x);
		c.setQta(1);
		list.add(c);
		Ordine_Bean ordine = mock(Ordine_Bean.class);
		when(ordine.getProdottiOrdinati()).thenReturn(list);
		when(ordine.getAzienda()).thenReturn(azienda);
		when(ordine.getCliente()).thenReturn(cliente);
		when(ordine.getCodiceCarta()).thenReturn("1234123412341234");
		when(ordine.getIndirizzoConsegna()).thenReturn("via pippo");
		when(ordine.getNote()).thenReturn("ciao ciao ciao ciao");
		when(ordine.getStato()).thenReturn(Ordine_Bean.IN_PREPARAZIONE);
		when(ordine.getTelefono()).thenReturn("1234567890");

		dao.creaOrdine(ordine);
		assertEquals(true, ordine.getCodiceID() != null);

	}

	@Test
	public void TestControlloEsistenzaOrdine() throws SQLException {
		GestoreUtenteDAOImpl us = mock(GestoreUtenteDAOImpl.class);
		GestoreOrdineDAOImpl dao = new GestoreOrdineDAOImpl(conn, us);

		boolean response1 = dao.controlloEsistenzaOrdine(4L);
		boolean response2 = dao.controlloEsistenzaOrdine(-5L);

		assertEquals(true, response1);
		assertEquals(false, response2);

	}

	@Test
	public void TestDammiOrdiniPreparazione() throws SQLException {
		GestoreUtenteDAOImpl us = mock(GestoreUtenteDAOImpl.class);
		GestoreOrdineDAOImpl dao = new GestoreOrdineDAOImpl(conn, us);
		AccountAzienda_Bean azienda = mock(AccountAzienda_Bean.class);
		when(azienda.getEmail()).thenReturn("azienda1@gmail.com");

		List<Ordine_Bean> ordini = dao.dammiOrdiniPreparazione(azienda);

		assertEquals(true, ordini != null);
		assertEquals(true, ordini.size() > 0);

	}

	@Test
	public void TestDammiOrdine() throws SQLException {
		GestoreUtenteDAOImpl us = mock(GestoreUtenteDAOImpl.class);
		GestoreOrdineDAOImpl dao = new GestoreOrdineDAOImpl(conn, us);

		Ordine_Bean ordine = dao.dammiOrdine(6L);
		assertEquals(true, ordine != null);
		assertEquals(6L, ordine.getCodiceID());
	}

	@Test
	public void TestDammiConsegne() throws SQLException {
		GestoreUtenteDAOImpl us = mock(GestoreUtenteDAOImpl.class);
		GestoreOrdineDAOImpl dao = new GestoreOrdineDAOImpl(conn, us);
		AccountFattorino_Bean fattorino = mock(AccountFattorino_Bean.class);
		when(fattorino.getEmail()).thenReturn("fattorino1@gmail.com");
		
		List<Ordine_Bean> consegne = dao.dammiConsegne(fattorino);

		assertEquals(true, consegne != null);
		assertEquals(true, consegne.size() > 0);
	}

	@Test
	public void TestSetRitirato() throws SQLException {

		GestoreUtenteDAOImpl us = mock(GestoreUtenteDAOImpl.class);
		GestoreOrdineDAOImpl dao = new GestoreOrdineDAOImpl(conn, us);

		dao.ordineSetRitirato((long) 3);
		Ordine_Bean ordine = dao.dammiOrdine(3L);
		assertEquals(ordine.getStato(), Ordine_Bean.RITIRATO);

	}

	@Test
	public void TestSetConsegnato() throws SQLException {

		GestoreUtenteDAOImpl us = mock(GestoreUtenteDAOImpl.class);
		GestoreOrdineDAOImpl dao = new GestoreOrdineDAOImpl(conn, us);

		dao.ordineSetConsegnato(4L);
		Ordine_Bean order = dao.dammiOrdine(4L);
		System.out.println(order.getStato());
		assertEquals(order.getStato(), Ordine_Bean.CONSEGNATO);

	}

}

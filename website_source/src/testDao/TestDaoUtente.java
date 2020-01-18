package testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.bean.AccountAzienda_Bean;
import model.bean.AccountCliente_Bean;
import model.bean.AccountFattorino_Bean;
import model.bean.AccountModeratore_Bean;
import model.bean.AccountUtenteRegistrato_Bean;

class TestDaoUtente extends Mockito{

	Connection conn;

	@BeforeEach
	void setUpConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eatreorder?serverTimezone="
				+ TimeZone.getDefault().getID() + "&useSSL=false&allowPublicKeyRetrieval=true", "root", "password");
	}

	@AfterEach
	void dismissConnection() throws SQLException {
		conn.close();
	}

	
	@Test
	public void TestControlloEsistenzaMail() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		boolean response = dao.controlloEsistenzaMail("rosariogagliardi@msn.com");
		boolean response2 = dao.controlloEsistenzaMail("pippo@msn.com");
		
		assertEquals(true, response);
		assertEquals(false, response2);
		
	}
	
	@Test
	public void TestRegistrazioneCliente() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		AccountCliente_Bean cliente = new AccountCliente_Bean("pippo@gmail.com", "12345", "Pippo", "Pluto");
		dao.registrazioneCliente(cliente);
		
		assertEquals(true,conn.createStatement().executeQuery("select * from cliente where email ='pippo@gmail.com'").next());
		conn.createStatement().executeUpdate("delete from cliente where email ='pippo@gmail.com'");
		conn.createStatement().executeUpdate("delete from utenteregistrato where email ='pippo@gmail.com'");
	}
	@Test
	public void TestRegistrazioneAzienda() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		AccountAzienda_Bean azienda = mock(AccountAzienda_Bean.class);
		when(azienda.getNome()).thenReturn("da Pippo");
		when(azienda.getEmail()).thenReturn("azienda1987@gmail.com");
		when(azienda.getPassword()).thenReturn("password");
		when(azienda.getVia()).thenReturn("via");
		when(azienda.getProvincia()).thenReturn("NA");
		when(azienda.getCitta()).thenReturn("salerno");
		when(azienda.getTelefono()).thenReturn("123");
		when(azienda.getPartitaIva()).thenReturn("123456");
		when(azienda.getNumeroCivico()).thenReturn(10);
		when(azienda.getOrarioDiApertura()).thenReturn(LocalTime.parse("12:00"));
		when(azienda.getOrarioDiChiusura()).thenReturn(LocalTime.parse("02:00"));
		Set<DayOfWeek> array = new HashSet<DayOfWeek>();
		array.add(DayOfWeek.SATURDAY);
		array.add(DayOfWeek.SUNDAY);
		when(azienda.getGiorniDiApertura()).thenReturn(array);
		dao.registrazioneAzienda(azienda);
		
		assertEquals(true,conn.createStatement().executeQuery("select * from azienda where email ='azienda1987@gmail.com'").next());
		conn.createStatement().executeUpdate("delete from azienda where email ='azienda1987@gmail.com'");
		conn.createStatement().executeUpdate("delete from utenteregistrato where email ='azienda1987@gmail.com'");
		conn.createStatement().executeUpdate("delete from giornilavorativi where email ='azienda1987@gmail.com'");
	}
	
	@Test
	public void TestRegistrazioneFattorino() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		AccountFattorino_Bean fattorino = mock(AccountFattorino_Bean.class);
		when(fattorino.getNome()).thenReturn("Marcolino");
		when(fattorino.getCognome()).thenReturn("Marcolino");
		when(fattorino.getEmail()).thenReturn("fattorinomarcolino7@gmail.com");
		when(fattorino.getPassword()).thenReturn("password");
		
		
		when(fattorino.getProvinciaConsegna()).thenReturn("NA");
		when(fattorino.getCittaConsegna()).thenReturn("salerno");
		when(fattorino.getTelefono()).thenReturn("123");
		
		when(fattorino.getInizioConsegne()).thenReturn(LocalTime.parse("12:00"));
		when(fattorino.getFineConsegne()).thenReturn(LocalTime.parse("02:00"));
		Set<DayOfWeek> array = new HashSet<DayOfWeek>();
		array.add(DayOfWeek.SATURDAY);
		array.add(DayOfWeek.SUNDAY);
		when(fattorino.getGiorniDiConsegna()).thenReturn(array);
		dao.registrazioneFattorino(fattorino);
		
		assertEquals(true,conn.createStatement().executeQuery("select * from fattorino where email ='fattorinomarcolino7@gmail.com'").next());
		conn.createStatement().executeUpdate("delete from azienda where email ='fattorinomarcolino7@gmail.com'");
		conn.createStatement().executeUpdate("delete from utenteregistrato where email ='fattorinomarcolino7@gmail.com'");
		conn.createStatement().executeUpdate("delete from giornilavorativi where email ='fattorinomarcolino7@gmail.com'");
	}
	
	@Test
	public void TestControllaBan() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		boolean res = dao.controllaBan("azienda1@gmail.com");
		assertEquals(false, res);
		conn.createStatement().executeUpdate("update utenteregistrato set is_banned=1 where email ='azienda1@gmail.com'");
		boolean res1 = dao.controllaBan("azienda1@gmail.com");
		assertEquals(true, res1);
		conn.createStatement().executeUpdate("update utenteregistrato set is_banned=0 where email ='azienda1@gmail.com'");
		
	}
	
	@Test
	public void TestControllaEsistenzaAccount() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		boolean res = dao.controllaEsistenzaAccount("azienda1@gmail.com","pippo1234");
		assertEquals(true, res);
		boolean res2 = dao.controllaEsistenzaAccount("azienda1@gmail.com","1234");
		assertEquals(false, res2);
	}
	
	@Test
	public void TestDammiUtenteCliente() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		AccountUtenteRegistrato_Bean acc = dao.dammiUtente("rosariogagliardi@msn.com");
		assertEquals(true, acc.getClass() == AccountCliente_Bean.class);
		
	}
	@Test
	public void TestDammiUtenteAzienda() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		AccountUtenteRegistrato_Bean acc = dao.dammiUtente("azienda1@gmail.com");
		assertEquals(true, acc.getClass() == AccountAzienda_Bean.class);
		
	}
	
	@Test
	public void TestDammiUtenteFattorino() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		AccountUtenteRegistrato_Bean acc = dao.dammiUtente("fattorino1@gmail.com");
		assertEquals(true, acc.getClass() == AccountFattorino_Bean.class);
		
	}
	
	@Test
	public void TestDammiUtenteModeratore() throws SQLException {
		
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		
		AccountUtenteRegistrato_Bean acc = dao.dammiUtente("eatsystem@eat.com");
		assertEquals(true, acc.getClass() == AccountModeratore_Bean.class);
		
	}
	
	
}

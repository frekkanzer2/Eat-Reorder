package testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
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
import model.bean.Prodotto_Bean;

class TestDaoUtente extends Mockito {

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

		assertEquals(true,
				conn.createStatement().executeQuery("select * from cliente where email ='pippo@gmail.com'").next());
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

		assertEquals(true, conn.createStatement()
				.executeQuery("select * from azienda where email ='azienda1987@gmail.com'").next());
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

		assertEquals(true, conn.createStatement()
				.executeQuery("select * from fattorino where email ='fattorinomarcolino7@gmail.com'").next());
		conn.createStatement().executeUpdate("delete from fattorino where email ='fattorinomarcolino7@gmail.com'");
		conn.createStatement()
				.executeUpdate("delete from utenteregistrato where email ='fattorinomarcolino7@gmail.com'");
		conn.createStatement()
				.executeUpdate("delete from giornilavorativi where email ='fattorinomarcolino7@gmail.com'");
	}

	@Test
	public void TestControllaBan() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);

		boolean res = dao.controllaBan("azienda1@gmail.com");
		assertEquals(false, res);
		conn.createStatement()
				.executeUpdate("update utenteregistrato set is_banned=1 where email ='azienda1@gmail.com'");
		boolean res1 = dao.controllaBan("azienda1@gmail.com");
		assertEquals(true, res1);
		conn.createStatement()
				.executeUpdate("update utenteregistrato set is_banned=0 where email ='azienda1@gmail.com'");

	}

	@Test
	public void TestControllaEsistenzaAccount() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);

		boolean res = dao.controllaEsistenzaAccount("azienda1@gmail.com", "pippo1234");
		assertEquals(true, res);
		boolean res2 = dao.controllaEsistenzaAccount("azienda1@gmail.com", "1234");
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

	@Test
	public void TestAggiornaCliente() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);

		AccountCliente_Bean clientNew = new AccountCliente_Bean("rosariogagliardi@msn.com", "2345", "Paperino",
				"Topolino");

		dao.aggiornaCliente(clientNew);
		AccountCliente_Bean clienteNew2 = (AccountCliente_Bean) dao.dammiUtente("rosariogagliardi@msn.com");
		assertEquals(clientNew.getCognome(), clienteNew2.getCognome());
		assertEquals(clientNew.getNome(), clienteNew2.getNome());
		assertEquals(clientNew.getPassword(), clienteNew2.getPassword());

		conn.createStatement().executeUpdate(
				"update Cliente JOIN UtenteRegistrato on Cliente.email=UtenteRegistrato.email set Cliente.nome='Rosario', Cliente.cognome='Gagliardi', UtenteRegistrato.pass='pallina1234' where Cliente.email='rosariogagliardi@msn.com' and UtenteRegistrato.email='rosariogagliardi@msn.com'");
	}

	@Test
	public void TestAggiornaAzienda() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);

		AccountAzienda_Bean azienda1 = mock(AccountAzienda_Bean.class);
		when(azienda1.getNome()).thenReturn("da Pluto");
		when(azienda1.getEmail()).thenReturn("pizzapanini@gmail.com");
		when(azienda1.getPassword()).thenReturn("password1");
		when(azienda1.getVia()).thenReturn("via2");
		when(azienda1.getProvincia()).thenReturn("NE");
		when(azienda1.getCitta()).thenReturn("praga");
		when(azienda1.getTelefono()).thenReturn("123456");
		when(azienda1.getPartitaIva()).thenReturn("1234568798");
		when(azienda1.getNumeroCivico()).thenReturn(11);
		when(azienda1.getOrarioDiApertura()).thenReturn(LocalTime.parse("11:00"));
		when(azienda1.getOrarioDiChiusura()).thenReturn(LocalTime.parse("03:00"));
		Set<DayOfWeek> array = new HashSet<DayOfWeek>();
		array.add(DayOfWeek.SATURDAY);
		array.add(DayOfWeek.SUNDAY);
		when(azienda1.getGiorniDiApertura()).thenReturn(array);

		dao.aggiornaAzienda(azienda1);

		AccountAzienda_Bean azienda2 = (AccountAzienda_Bean) dao.dammiUtente("pizzapanini@gmail.com");

		assertEquals(azienda1.getCitta(), azienda2.getCitta());
		assertEquals(azienda1.getNome(), azienda2.getNome());
		assertEquals(azienda1.getPassword(), azienda2.getPassword());
		assertEquals(azienda1.getVia(), azienda2.getVia());
		assertEquals(azienda1.getProvincia(), azienda2.getProvincia());
		assertEquals(azienda1.getTelefono(), azienda2.getTelefono());
		assertEquals(azienda1.getPartitaIva(), azienda2.getPartitaIva());
		assertEquals(azienda1.getNumeroCivico(), azienda2.getNumeroCivico());
		assertEquals(azienda1.getOrarioDiApertura().toString(), azienda2.getOrarioDiApertura().toString());
		assertEquals(azienda1.getOrarioDiChiusura().toString(), azienda2.getOrarioDiChiusura().toString());
		assertEquals(azienda1.getGiorniDiApertura(), azienda2.getGiorniDiApertura());

		conn.createStatement().executeUpdate(
				"update Azienda JOIN UtenteRegistrato on Azienda.email=UtenteRegistrato.email set Azienda.nome='PizzaPanini', Azienda.via='san silvestro', Azienda.numero_civico=3, Azienda.citta='salerno', Azienda.provincia='CE', Azienda.partita_iva='12345678910', Azienda.telefono='2345', Azienda.orario_apertura='12:00:00', Azienda.orario_chiusura='13:00:00', UtenteRegistrato.pass='pippopluto' where Azienda.email='pizzapanini@gmail.com' and UtenteRegistrato.email='pizzapanini@gmail.com'");
		conn.createStatement().executeUpdate("delete from giornilavorativi where email ='pizzapanini@gmail.com'");
		conn.createStatement().executeUpdate("delete from giornilavorativi where email ='pizzapanini@gmail.com'");
		conn.createStatement().executeUpdate(
				"insert into giornilavorativi (email,giorno) values ('pizzapanini@gmail.com','MONDAY'),('pizzapanini@gmail.com','TUESDAY')");

	}

	@Test
	public void TestAggiornaFattorino() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);

		AccountFattorino_Bean fattorino = mock(AccountFattorino_Bean.class);
		when(fattorino.getNome()).thenReturn("Marcolino");
		when(fattorino.getCognome()).thenReturn("Marcolino");
		when(fattorino.getEmail()).thenReturn("fattorino1@gmail.com");
		when(fattorino.getPassword()).thenReturn("password");

		when(fattorino.getProvinciaConsegna()).thenReturn("NA");
		when(fattorino.getCittaConsegna()).thenReturn("salerno");
		when(fattorino.getTelefono()).thenReturn("123");

		when(fattorino.getInizioConsegne()).thenReturn(LocalTime.parse("11:00"));
		when(fattorino.getFineConsegne()).thenReturn(LocalTime.parse("02:00"));
		Set<DayOfWeek> array = new HashSet<DayOfWeek>();
		array.add(DayOfWeek.SATURDAY);
		array.add(DayOfWeek.SUNDAY);
		when(fattorino.getGiorniDiConsegna()).thenReturn(array);

		dao.aggiornaFattorino(fattorino);

		AccountFattorino_Bean fattorino2 = (AccountFattorino_Bean) dao.dammiUtente("fattorino1@gmail.com");

		assertEquals(fattorino.getCittaConsegna(), fattorino2.getCittaConsegna());
		assertEquals(fattorino.getNome(), fattorino2.getNome());
		assertEquals(fattorino.getCognome(), fattorino2.getCognome());
		assertEquals(fattorino.getPassword(), fattorino2.getPassword());
		assertEquals(fattorino.getProvinciaConsegna(), fattorino2.getProvinciaConsegna());
		assertEquals(fattorino.getTelefono(), fattorino2.getTelefono());
		assertEquals(fattorino.getInizioConsegne().toString(), fattorino2.getInizioConsegne().toString());

		assertEquals(fattorino.getFineConsegne().toString(), fattorino2.getFineConsegne().toString());

		assertEquals(fattorino.getGiorniDiConsegna(), fattorino2.getGiorniDiConsegna());

		conn.createStatement().executeUpdate(
				"update fattorino JOIN UtenteRegistrato on fattorino.email=UtenteRegistrato.email set UtenteRegistrato.pass='fattorino1234',"
						+ "fattorino.nome ='Marco',fattorino.cognome='Dello Buono',fattorino.telefono='0823546547', fattorino.citta_consegna='Salerno',fattorino.provincia ='SA',"
						+ " fattorino.orario_inizio='12:00:00',fattorino.orario_fine='23:00:00' where fattorino.email='fattorino1@gmail.com' and UtenteRegistrato.email='fattorino1@gmail.com'");
		conn.createStatement().executeUpdate("delete from giornilavorativi where email ='fattorino1@gmail.com'");
		conn.createStatement().executeUpdate("delete from giornilavorativi where email ='fattorino1@gmail.com'");
		conn.createStatement().executeUpdate(
				"insert into giornilavorativi (email,giorno) values ('fattorino1@gmail.com','MONDAY'),('fattorino1@gmail.com','TUESDAY')");

	}

	@Test
	public void TestDammiAziendaOrdine() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);

		AccountAzienda_Bean azienda = dao.dammiAziendaConOrdine(4L);
		assertNotNull(azienda);

	}

	@Test
	public void TestDammiListaAziende() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);

		List<AccountAzienda_Bean> aziende = dao.dammiListaAziende("salerno");
		assertNotNull(aziende);

	}

	@Test
	public void TestBannaAzienda() throws SQLException {

		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		AccountAzienda_Bean azienda1 = mock(AccountAzienda_Bean.class);

		when(azienda1.getEmail()).thenReturn("pizzapanini@gmail.com");
		dao.banAzienda(azienda1);
		boolean response = dao.controllaBan(azienda1.getEmail());
		assertTrue(response);

		conn.createStatement()
				.executeUpdate("update utenteregistrato set is_banned=0 where email ='pizzapanini@gmail.com'");

	}

	@Test

	public void TestAggiungiAlListino() throws MalformedURLException, SQLException {
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		AccountAzienda_Bean azienda1 = mock(AccountAzienda_Bean.class);

		when(azienda1.getEmail()).thenReturn("pizzapanini@gmail.com");
		when(azienda1.getNome()).thenReturn("PizzaPanini");
		Prodotto_Bean prod = new Prodotto_Bean();

		prod.setDescrizione("ciao ciao caio");
		prod.setImmagine(new URL("http://doggo.com"));
		prod.setNome("Cibo per cani");
		prod.setPrezzo(7.5f);
		prod.setAzienda(azienda1);

		dao.aggiungiAlListino(azienda1, prod);
		azienda1 = (AccountAzienda_Bean) dao.dammiUtente("pizzapanini@gmail.com");

		assertTrue(azienda1.getProdotti().containsKey(prod.getCodice()));
		conn.createStatement().executeUpdate("delete from prodotto where codice =" + prod.getCodice());

	}

	public void TestModificaProdotto() throws MalformedURLException, SQLException {
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
		AccountAzienda_Bean azienda1 = mock(AccountAzienda_Bean.class);

		when(azienda1.getEmail()).thenReturn("pizzapanini@gmail.com");
		when(azienda1.getNome()).thenReturn("PizzaPanini");
		Prodotto_Bean prod = new Prodotto_Bean();
		prod.setCodice(12L);
		prod.setDescrizione("ciao");
		prod.setImmagine(new URL("http://dog.it"));
		prod.setNome("Cibo per gatti");
		prod.setPrezzo(71.5f);
		prod.setAzienda(azienda1);

		dao.aggiornaProdotto(azienda1, prod);
		azienda1 = (AccountAzienda_Bean) dao.dammiUtente("pizzapanini@gmail.com");

		Prodotto_Bean retrieved = azienda1.getProdotti().get(12L);
		assertEquals(retrieved.getDescrizione(), prod.getDescrizione());
		assertEquals(retrieved.getImmagine(), prod.getImmagine());
		assertEquals(retrieved.getNome(), prod.getNome());
		assertEquals(retrieved.getPrezzo(), prod.getPrezzo());

		
		conn.createStatement().executeUpdate("delete from prodotto where codice =" + retrieved.getCodice());

		Prodotto_Bean prod2 = new Prodotto_Bean();

		prod.setDescrizione("ciao ciao caio");
		prod.setImmagine(new URL("http://doggo.com"));
		prod.setNome("Cibo per cani");
		prod.setPrezzo(7.5f);
		prod.setAzienda(azienda1);

		dao.aggiungiAlListino(azienda1, prod2);

	}
	
	@Test
	public void TestRimuoviProdotto() throws SQLException {
		GestoreUtenteDAOImpl dao = new GestoreUtenteDAOImpl(conn);
	

		conn.createStatement().executeUpdate("insert into prodotto (codice, nome, descrizione,prezzo,path_immagine,azienda,email) values(78,'Pippo','piiiiii',7.55,'http://','PizzaPanini','pizzapanini@gmail.com')");
		AccountAzienda_Bean azienda1 = mock(AccountAzienda_Bean.class);

		when(azienda1.getEmail()).thenReturn("pizzapanini@gmail.com");
		when(azienda1.getNome()).thenReturn("PizzaPanini");
		Prodotto_Bean prod = new Prodotto_Bean();
		prod.setCodice(78L);
		dao.rimuoviProdotto(azienda1, prod);
		
		azienda1 = (AccountAzienda_Bean) dao.dammiUtente("pizzapanini@gmail.com");
		assertFalse(azienda1.getProdotti().containsKey(78L));
		
		
	}

	
}

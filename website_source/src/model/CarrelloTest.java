package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.bean.AccountAzienda_Bean;
import model.bean.Prodotto_Bean;

public class CarrelloTest {
	static Carrello cart;
	static AccountAzienda_Bean azienda1;
	static AccountAzienda_Bean azienda2;
	static Prodotto_Bean x;
	static Prodotto_Bean x2;
	static Prodotto_Bean x3;

	@BeforeClass
	public static void initializeCart() {
		cart = new Carrello();

		azienda1 = new AccountAzienda_Bean("bbb@example.com", "prova", "", "", 0, "", "", "", null, null, null, null);
		x = new Prodotto_Bean();
		x.setCodice(1L);
		x.setAzienda(azienda1);
		azienda2 = new AccountAzienda_Bean("aaa@example.com", "prova", "", "", 0, "", "", "", null, null, null, null);
		x2 = new Prodotto_Bean();
		x2.setAzienda(azienda2);
		x2.setCodice(2L);
		x3 = new Prodotto_Bean();
		x3.setCodice(3L);
		x3.setAzienda(azienda2);
	}

	@Before
	public void svuota() {
		cart.svuota();
	}

	@Test
	public void addProductEmptyCart() {
		Prodotto_Bean x = new Prodotto_Bean();
		x.setAzienda(azienda2);
		cart.aggiungiProdotto(x);
		assertTrue(cart.checkInCarrello(x));
		assertEquals(cart.getCurrentAzienda().getEmail(), x.getAzienda().getEmail());
	}

	@Before
	public void insert() {
		cart.aggiungiProdotto(x2);
	}

	@Test
	public void addProductNotEmptySameAzienda() {

		cart.aggiungiProdotto(x3);
		assertTrue(cart.checkInCarrello(x3));
		assertEquals(cart.getCurrentAzienda().getEmail(), x3.getAzienda().getEmail());
	}

	@Test
	public void addProductNotEmptyNotSameAzienda() {

		cart.aggiungiProdotto(x);
		assertFalse(cart.checkInCarrello(x));

	}

	@Test
	public void changeQuantityMaggiore0() {
		cart.aggiornaQtaCarrello(x2, 2);
		assertTrue(cart.getProdotto(x2.getCodice()).getQta() == 2);
	}

	@Test
	public void changeQuantityEquals0() {
		cart.aggiornaQtaCarrello(x2, 0);
		assertTrue(cart.getProdotto(x2.getCodice()).getQta() != 0);
	}

	@Test
	public void changeQuantityMinore0() {
		cart.aggiornaQtaCarrello(x2, -2);
		assertTrue(cart.getProdotto(x2.getCodice()).getQta() > 0);
	}

	@Test
	public void rimuoviProdotto() {
		cart.rimuoviProdotto(x2);
		assertFalse(cart.checkInCarrello(x2));
	}

	@Test
	public void checkAzienda() {
		assertTrue(cart.getCurrentAzienda().getEmail().equals(x2.getAzienda().getEmail()));
	}

}

package model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.bean.AccountAzienda_Bean;
import model.bean.Prodotto_Bean;

public class CarrelloTest {
	Carrello cart;
	AccountAzienda_Bean azienda1;
	AccountAzienda_Bean azienda2;
	
	@BeforeClass
	public void initializeCart() {
		cart = new Carrello();
		
		azienda1 = new AccountAzienda_Bean("bbb@example.com", "prova","","",0, "","","", null, null, null, null);
		azienda2 = new AccountAzienda_Bean("aaa@example.com", "prova","","",0, "","","", null, null, null, null);
	}
	
	@Test
	public void addProductEmptyCart() {
		Prodotto_Bean x = new Prodotto_Bean();
		x.setAzienda(azienda2);
		cart.aggiungiProdotto(x);
		assertTrue(cart.checkInCarrello(x));
		assertEquals(cart.getCurrentAzienda(), x.getAzienda());
	}
	

}

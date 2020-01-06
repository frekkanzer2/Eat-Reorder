package model;

import model.bean.Prodotto_Bean;

/**
 * Classe che gestisce un prodotto e la relativa quantità
 * @author Rosario
 *
 */
public class ProdottoQuantita {

	private Prodotto_Bean prodotto;
	private Integer qta;
	/**
	 * 
	 * @return {@link Prodotto_Bean} 
	 */
	public Prodotto_Bean getProdotto() {
		return prodotto;
	}
	
	/**
	 * 
	 * @param prodotto {@link Prodotto_Bean}
	 */
	public void setProdotto(Prodotto_Bean prodotto) {
		this.prodotto = prodotto;
	}
	
	/**
	 * 
	 * @return {@link Integer} quantita'
	 */
	public Integer getQta() {
		return qta;
	}
	
	/**
	 * 
	 * @param qta quantità del prodotto
	 */
	public void setQta(Integer qta) {
		this.qta = qta;
	}
}

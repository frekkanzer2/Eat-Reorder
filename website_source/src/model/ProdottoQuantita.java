package model;

import model.bean.Prodotto_Bean;

public class ProdottoQuantita {

	private Prodotto_Bean prodotto;
	private Integer qta;
	
	public Prodotto_Bean getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto_Bean prodotto) {
		this.prodotto = prodotto;
	}
	public Integer getQta() {
		return qta;
	}
	public void setQta(Integer qta) {
		this.qta = qta;
	}
}

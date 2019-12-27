package model;

import interfaces.GestoreMail_Interface;
import model.bean.AccountAzienda_Bean;
import model.bean.Ordine_Bean;

public class GestoreMail implements GestoreMail_Interface {

	public GestoreMail() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inviaMailModeratore(Long idOrder, String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inviaMailBan(AccountAzienda_Bean azienda, String motivation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inviaMailOrdine(Ordine_Bean ordine) {
		// TODO Auto-generated method stub

	}

}

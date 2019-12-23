package interfaces;

import model.AccountAzienda;
import model.Ordine;

public interface GestoreMail {

	public void inviaMailModeratore(Long idOrder, String description);
	public void inviaMailBan(AccountAzienda azienda, String motivation);
	public void inviaMailOrdine(Ordine ordine);
}

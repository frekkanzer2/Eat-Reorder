package interfaces;

import javax.mail.MessagingException;

import model.bean.AccountAzienda_Bean;
import model.bean.Ordine_Bean;
/**
 * Interfaccia per il Gestore Mail
 * @author Rosario
 *
 */
public interface GestoreMail_Interface {
	public void inviaMailModeratore(Long idOrder, String description) throws Exception;
	public void inviaMailBan(AccountAzienda_Bean azienda, String motivation) throws MessagingException;
	public void inviaMailOrdine(Ordine_Bean ordine) throws MessagingException;
}

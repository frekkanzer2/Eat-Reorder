package interfaces;

import javax.mail.MessagingException;

import model.bean.AccountAzienda_Bean;
import model.bean.Ordine_Bean;
/**
 * Interfaccia per il Gestore Mail
 * 
 *
 */
public interface GestoreMail_Interface {
	/**
	 * 
	 * @param idOrder id Dell'ordine
	 * @param description descrizione di invio mail al Moderatore
	 * @throws Exception eventuali errori dovuti all'invio di mail
	 */
	public void inviaMailModeratore(Long idOrder, String description) throws Exception;
	
	/**
	 * 
	 * @param azienda account di una azienda da bannare
	 * @param motivation motivazione del ban
	 * @throws MessagingException eventuali errori dovuti all'invio di mail
	 */
	public void inviaMailBan(AccountAzienda_Bean azienda, String motivation) throws MessagingException;
	/**
	 * 
	 * @param ordine ordine con i dettagli da inviare al cliente
	 * @throws MessagingException eventuali errori dovuti all'invio di mail
	 */
	public void inviaMailOrdine(Ordine_Bean ordine) throws MessagingException;
}

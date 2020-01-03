package model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	
	
	 private static void sendMail (String dest, String mitt, String oggetto, String testoEmail)
	      throws MessagingException
	  {
	    // Creazione di una mail session
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "127.0.0.1");//used for SMTPFakeMail
	    Session session = Session.getDefaultInstance(props);

	    // Creazione del messaggio da inviare
	    MimeMessage message = new MimeMessage(session);
	    message.setSubject(oggetto);
	    message.setText(testoEmail);

	    // Aggiunta degli indirizzi del mittente e del destinatario
	    InternetAddress fromAddress = new InternetAddress(mitt);
	    InternetAddress toAddress = new InternetAddress(dest);
	    message.setFrom(fromAddress);
	    
	    message.setRecipient(Message.RecipientType.TO, toAddress);

	    // Invio del messaggio
	    Transport.send(message);
	  }
	 
	 public static void main(String args[]) throws MessagingException {
		 
		 sendMail("rosariogagliardi@msn.com", "Eat&Reorder-Service@eat.com", "Hello", "Hello World");
	 }
	

}

package technicalservices;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.util.ByteArrayDataSource;

import model.purchases.Purchase;

import java.io.ByteArrayOutputStream;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

	public class MailUtility{
		
		private static String host = "smtp.gmail.com";
		private static String from = "technoworldcustommersservice@gmail.com";
		private static String pass = "bsuhdwsgqqonqvhu";
		
		public static void sendRecipt(Purchase order, String dest, String imagePath) throws Exception{
           
		       
	        String content = "Ciao " + order.getUser() + ",\nGrazie per aver acquistato su TecnhoWorld.it!\n\nTi ricordiamo la modalità di spedizone selezionata:\n" +
	        order.getShipment() + "\n\n Inoltre puoi trovare in allegato la ricevuta di acquisto.\n\nPer qualsiasi informazione riguardante l'ordine contatta il nostro servizio"
	        		+ "clienti al seguente indirizzo " + from;
	        String subject = "Conferma ordine " + order.getId();      
	         
	        
	        Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", MailUtility.host);
			props.put("mail.smtp.user", MailUtility.from);
			props.put("mail.smtp.password", MailUtility.pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props,null);

	        ByteArrayOutputStream outputStream = null;
	         
	      
	            MimeBodyPart textBodyPart = new MimeBodyPart();
	            textBodyPart.setText(content);
	            outputStream = new ByteArrayOutputStream();
	            PdfCreator.generatePurchaseReceipt(outputStream, order, imagePath);

	            byte[] bytes = outputStream.toByteArray();
	            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	            MimeBodyPart pdfBodyPart = new MimeBodyPart();
	            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
	            pdfBodyPart.setFileName("Ricevuta Ordine: " + order.getId());
	                         
	            MimeMultipart mimeMultipart = new MimeMultipart();
	            mimeMultipart.addBodyPart(textBodyPart);
	            mimeMultipart.addBodyPart(pdfBodyPart);
	             
	            InternetAddress iaSender = new InternetAddress(from);
	            InternetAddress iaRecipient = new InternetAddress(dest);
	             
	            MimeMessage mimeMessage = new MimeMessage(session);
	            mimeMessage.setSender(iaSender);
	            mimeMessage.setSubject(subject);
	            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
	            mimeMessage.setContent(mimeMultipart);
	             
	            Transport transport = session.getTransport("smtp");
				transport.connect(host,from,pass);
				transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
				transport.close();        
		}
		
		public static void sendRegistrationMail(String dest, String username, String password) throws MessagingException{
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", MailUtility.host);
			props.put("mail.smtp.user", MailUtility.from);
			props.put("mail.smtp.password", MailUtility.pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			
			Session session = Session.getDefaultInstance(props,null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, dest);
			message.setSubject("Conferma registrazione technoWorld");
			message.setText("Ciao " + username + " grazie per esserti registrato su TechnoWorld.it!\n\n"
					+ "Ti ricordiamo le credenziali da te inserite:\nUsername: "+username+"\nPassword: "+password+" \n\n"
					+ "Ti auguriamo una piacevole navigazione!\nCordiali Saluti!\n\n Il team technoWorld\n\n\n\n"
					+ "Non rispondere a questa e-mail in quanto è stata generata automaticamente.\n ");
			Transport transport = session.getTransport("smtp");
			transport.connect(host,from,pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
		}
		
		public static void notifyChangeStatus(Purchase purchase, String dest) throws MessagingException{
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", MailUtility.host);
			props.put("mail.smtp.user", MailUtility.from);
			props.put("mail.smtp.password", MailUtility.pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			
			Session session = Session.getDefaultInstance(props,null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, dest);
			message.setSubject("Notifica cambiamento di stato");
			String text = null;
			if(purchase.getStatus() == 2)
				text = "Ciao " + purchase.getUser()+ ",\n\nIl tuo ordine n. " + purchase.getId() + "è pronto per essere ritirato presso il nostro negozio " + purchase.getShipment() +"."
					+ "\nTi ricordiamo di presentare la ricevuta di acquisto al momento del ritiro!\n"
					+ "Grazie per aver acquistato su TechnoWorld.it!";
			if(purchase.getStatus() == 3)
				text = "Ciao " + purchase.getUser()+ ",\n\nIl tuo ordine n. " + purchase.getId() + "è stato ritirato presso il nostro negozio " + purchase.getShipment() +"."
						+ "Grazie per aver acquistato su TechnoWorld.it!";
			message.setText(text);
			Transport transport = session.getTransport("smtp");
			transport.connect(host,from,pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
}

	

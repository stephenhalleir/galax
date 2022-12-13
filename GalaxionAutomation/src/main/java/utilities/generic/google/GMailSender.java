package utilities.generic.google;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import utilities.config.ConfigReader;

public class GMailSender {

	public static void main(String[] args) {
		String from = ConfigReader.getConfigValue("email_username");
		String pass = ConfigReader.getConfigValue("email_password");
		String[] to = { "stephen.hall@eir.ie" }; // list of recipient email addresses
		String subject = "Galaxion Gmail Test";
		String body = "<h1>Send email test is working!</h1>";

		sendFromGMail(from, pass, to, subject, body,null);
	}

	// send an email via G-Mail
	public static void sendFromGMail(String from, String pass, String[] to, String subject, String body, String attachment) {
		final String username = ConfigReader.getConfigValue("email_username");
		final String password = ConfigReader.getConfigValue("email_password");

		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			
			InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            // Set To: header field of the header
            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			//messageBodyPart.setText(body);
			messageBodyPart.setContent(body, "text/html");

			// Create a multipart message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			
			// if there is an attachment required, link it
			if(attachment != null) {
				DataSource source = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(new File(attachment).getName());
				multipart.addBodyPart(messageBodyPart); 
			}

			try {
				// Send the complete message parts
				message.setContent(multipart);
	
				// Send message
				Transport.send(message);
			}
			catch(Exception e) {
				System.err.println("GmailSender(): Email not sent - " + e.getMessage());
			}

			System.out.println("Email message successfully with attachment " + attachment);

		} catch (MessagingException e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
	}
}

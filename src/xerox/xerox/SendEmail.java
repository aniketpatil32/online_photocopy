package xerox;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import xerox.authenticate.data;

import javax.activation.*;

public class SendEmail {
	public static class SmtpAuthenticator extends Authenticator {
		public SmtpAuthenticator() {

		    super();
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
		 String username =data.help_email;
		 String password =data.help_password;
		    if ((username != null) && (username.length() > 0) && (password != null) 
		      && (password.length   () > 0)) {

		        return new PasswordAuthentication(username, password);
		    }

		    return null;
		}
	}
   public static void send() {
	  
	   SmtpAuthenticator authentication = new SmtpAuthenticator();
	
      // Recipient's email ID needs to be mentioned.
      String to = data.email;

      // Sender's email ID needs to be mentioned
      String from =data.help_email;

      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com";
      
      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.put("mail.smtp.host" , "smtp.gmail.com");
      properties.put("mail.stmp.user" , data.help_email);

      //To use TLS
      properties.put("mail.smtp.auth", "true"); 
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.password",data.help_password);
      //To use SSL
      properties.put("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties,authentication);
      session.setDebug(true);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Password from Phoenix copier!");

         // Now set the actual message
         message.setText("password is : "+data.password);

         // Send message
         Transport transport=session.getTransport("smtp");
         transport.connect("smtp.gmail.com",data.help_email,data.help_password);
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}

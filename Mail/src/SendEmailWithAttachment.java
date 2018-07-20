import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailWithAttachment {
	 public static void main(String[] args) {
	        SendEmailWithAttachment demo = new SendEmailWithAttachment();
	        demo.sendEmail();
	    }

	    public void sendEmail() {
	        // Defines the E-Mail information.
	        String from = "bindub@ibt.example.com";
	        String to = "dhananjayp@ibt.example.com";
	        String subject = "Important Message";
	        String bodyText = "This is a important message with attachment.";

	        // The attachment file name.
	        String attachmentName = "abc.txt";

	        // Creates a Session with the following properties.
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "192.168.0.22");
			props.put("mail.smtp.port", "25");
	       
	        Session session = Session.getDefaultInstance(props);

	        try {
	            InternetAddress fromAddress = new InternetAddress(from);
	            InternetAddress toAddress = new InternetAddress(to);

	            // Create an Internet mail msg.
	            MimeMessage msg = new MimeMessage(session);
	            msg.setFrom(fromAddress);
	            msg.setRecipient(Message.RecipientType.TO, toAddress);
	            msg.setSubject(subject);
	            msg.setSentDate(new Date());

	            // Set the email msg text.
	            MimeBodyPart messagePart = new MimeBodyPart();
	            messagePart.setText(bodyText);

	            // Set the email attachment file
	            FileDataSource fileDataSource = new FileDataSource(attachmentName);

	            MimeBodyPart attachmentPart = new MimeBodyPart();
	            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
	            attachmentPart.setFileName(fileDataSource.getName());

	            // Create Multipart E-Mail.
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messagePart);
	            multipart.addBodyPart(attachmentPart);

	            msg.setContent(multipart);

	            // Send the msg. Don't forget to set the username and password
	            // to authenticate to the mail server.
	           // Transport.send(msg, "kodejava", "********");
	            Transport.send(msg);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	}
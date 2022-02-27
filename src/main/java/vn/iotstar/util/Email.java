package vn.iotstar.util;

import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import vn.iotstar.model.Users;

public class Email {
	// hàm mã code ngẫu nhiên
	public String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	// send email to the user email
	public Boolean sendEmail(Users user) {
		boolean test = false;

		String toEmail = user.getEmail();
		String fromEmail = "quang.tran@alta.com.vn";
		String password = "Quang@123";
		try {
			// your host email SMTP server details
			Properties pr = configEmail(new Properties());

			// get session to authenticate the host email address and password
			Session session = Session.getInstance(pr, new Authenticator() {
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(fromEmail, password);
//				}
				protected javax.mail.PasswordAuthentication getPasswordAuthentication () {
					return new javax.mail.PasswordAuthentication(fromEmail, password);
				}
			});

			// set email message detail
			Message mess = new MimeMessage(session);
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");
			// set from email address
			mess.setFrom(new InternetAddress(fromEmail));
			// set to email address or destination email address
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			// set email subject
			mess.setSubject("Confirm Code");

			// set message text
			mess.setText("Your is code: " + user.getCode());
			// send the message
			Transport.send(mess);

			test = true;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return test;
	}
	
	// send email to the user email

	

	public Properties configEmail(Properties pr) {
		// your host email SMTP server details
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		pr.put("mail.smtp.socketFactory.port", "587");
		pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return pr;
	}
}

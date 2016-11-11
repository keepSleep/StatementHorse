package com.member.model;

import java.util.Base64;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CheckMailService {

	public void sendCheckMail(String email) {
		final String username = "statementhorse@gmail.com";
		final String password = "s123456-";// your password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "*");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("statementhorse@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			message.setSubject("財報馬會員認證");
			message.setText(
					"親愛的使用者你好:\n請於6小時內點選下面以完成會員申請\n如認證網址無法點擊，請複製以下網址，貼至瀏覽器：\nhttp://localhost:8080/StatementHorse/account/registermailok?confirmation_token="
							+ Base64.getEncoder().encodeToString(email.getBytes()));

			Transport.send(message);

			System.out.println("寄送email結束");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendChangepw(String email) {
		final String username = "statementhorse@gmail.com";
		final String password = "s123456-";// your password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "*");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("statementhorse@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("財報馬會員密碼修改");
			message.setText(
					"親愛的使用者你好:\n請於6小時內點選下面連結完成密碼修改\n認證網址\n如認證網址無法點擊，請複製以下網址，貼至瀏覽器：\nhttp://localhost:8080/StatementHorse/login/changepw.jsp?confirmation_token="
							+ Base64.getEncoder().encodeToString(email.getBytes()));

			Transport.send(message);

			System.out.println("寄送email結束");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

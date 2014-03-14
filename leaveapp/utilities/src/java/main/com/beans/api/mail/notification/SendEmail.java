package com.beans.api.mail.notification;

import java.io.InputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	private static SendEmail sendMail = null;
	private static Properties props;
	private static Session session;

	// Constructor for to load property file
	SendEmail() {
		props = new Properties();
		try {
			System.out.println("in constructor");

			// String s=System.getProperty("user.dir");
			// java.io.File file=new java.io.File();

			// System.out.print(getClass().getResource("setting.properties").toString());

			InputStream in = getClass().getResourceAsStream(
					"setting.properties");

			props.load(in);
			// ResourceBundle.getBundle("settings.properties");
			// props.load(new FileInputStream(new File("settings.properties")));
			// PropertyResourceBundle rs = new PropertyResourceBundle(in);

			// props.load(rs);
			// session = Session.getDefaultInstance(props);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static SendEmail getInstance() {
		if (sendMail == null) {
			sendMail = new SendEmail();
		}
		System.out.println("in get instance method");
		return sendMail;

	}

	public static void sendMail(Sender email) {

		String sen = email.getSender();
		final String senderMail = sen;
		String rec = email.getReceiver();
		// String senderPasswor =email.getSenderPassword();
		String sub = email.getSubject();
		String text = email.getText();
		String psw = email.getSenderPassword();
		final String senderPsw = psw;

		/*
		 * Session session = Session.getInstance(props, new
		 * javax.mail.Authenticator() { protected PasswordAuthentication
		 * getPasswordAuthentication() { return new PasswordAuthentication(,
		 * password); } });
		 */

		System.out.println("in get send email method");

		try {
			Session session = Session.getDefaultInstance(props,
					new Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(senderMail,
									senderPsw);
						}
					});

			// Session session = Session.getInstance(props);

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(sen));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(rec));

			message.setSubject(sub);
			message.setText(text);
			// Multipart multipar = new MimeMultipart();

			// message.setContent(multipar,"html/text");
			// setContent("<h1>this is actual message </h1>","html/text");
			// message.setText(text);
			// BodyPart body = new MimeBodyPart();

			// Multipart multipart = new MimeMultipart();
			// multipart.addBodyPart(body);

			// body = new MimeBodyPart();

			// String filename = "hello.txt";
			// DataSource source = new FileDataSource(filename);
			// body.setDataHandler(new DataHandler(source));
			// body.setFileName(filename);
			// multipart.addBodyPart(body);
			// message.setContent(multipart, "text/html");

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		System.out.println("Done....");
	}

}

package com.aplicacion;

import javax.mail.*;
import javax.mail.internet.*;

import org.eclipse.persistence.sessions.Session;

import java.net.Authenticator;
import java.util.*;

public class EnviadorMail {
	private String miCorreo = "micorreo@gmail.com";
	private String miContraseña = "*******";
	private String servidorSMTP = "smtp.gmail.com";
	private String puertoEnvio = "465";
	private String mailReceptor = null;
	private String asunto = null;
	private String cuerpo = null;

	public void Enviar(String correo, String SMTP, String password,
			String mailReceptor, String asunto, String cuerpo) {
		this.miCorreo=correo;
		this.servidorSMTP=SMTP;
		this.miContraseña=password;
		this.mailReceptor = mailReceptor;
		this.asunto = asunto;
		this.cuerpo = cuerpo;

		Properties props = new Properties();
		props.put("mail.smtp.user", miCorreo);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", puertoEnvio);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		// SecurityManager security = System.getSecurityManager();

		try {
			autentificadorSMTP auth = new autentificadorSMTP();
			javax.mail.Session session = javax.mail.Session.getInstance(props,
					auth);
			// session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(cuerpo);
			msg.setSubject(asunto);
			msg.setFrom(new InternetAddress(miCorreo));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					mailReceptor));
			Transport.send(msg);
		} catch (Exception mex) {
			mex.printStackTrace();
		}

	}

	private class autentificadorSMTP extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(miCorreo, miContraseña);
		}
	}
}

package fr.bicomat.Auth.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import fr.bicomat.Auth.entities.EmailTemplate;
import fr.bicomat.Auth.entities.User_App;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}


	public void sendConfirmationEmail(final User_App user) {
		try {
			EmailTemplate template = new EmailTemplate("registerUser.html");

			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("user", user.getFirstName());
			replacements.put("ssoId", user.getSsoId());
			String subject = "[Agency] Activation de compte" ;
			String body = template.getTemplate(replacements);

			sendEmail (subject, user.getEmail(),body);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendNewPassWordEmail(final User_App user ) {
		try {
			EmailTemplate template = new EmailTemplate("resetpassword.html");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("user", user.getFirstName());
			replacements.put("pwd", user.getPassword());
			String subject = "[Agency] Initialisation du mot de passe" ;
			String body = template.getTemplate(replacements);
			sendEmail (subject, user.getEmail(),body);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendEmail(String subject, String to, String message) throws MessagingException{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("noreply@agency.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(message,true);

		mailSender.send(mimeMessage);
	}	   

}
package fr.bicomat.Auth.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import fr.bicomat.AgenceApplication;
import fr.bicomat.Auth.entities.EmailTemplate;
import fr.bicomat.Auth.entities.User_App;
import fr.bicomat.entities.Alerte;

@Component
public class EmailService {

	private static final Logger log = LoggerFactory.getLogger(EmailService.class);
	
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
	
	public void sendAlerte(final Alerte a) {
		try {
			EmailTemplate template = new EmailTemplate("sendAlerte.html");
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("user", a.getClient().getNomClient());
			replacements.put("titre",a.getTitre());
			replacements.put("message",a.getDescriptif());
			String subject = "[Agency] Alerte" ;
			String body = template.getTemplate(replacements);
			sendEmail (subject,  a.getClient().getEmail(),body);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erreur dans la création de l'alerte", e);
		}
	}	   

}
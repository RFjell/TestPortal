package se.rfjell.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class Mail {

	@Autowired
	private TemplateEngine emailTemplateEngine;

	private JavaMailSender javaMailSender;

	@Autowired
	Mail( JavaMailSender javaMailSender ) {
		this.javaMailSender = javaMailSender;
	}

	public void send(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom( from );
		message.setTo( to );
		message.setSubject( subject );
		message.setText( msg );

		javaMailSender.send( message );
	}

	public void sendValidationMail(String recipient, String validationLink) {
		Context ctx = new Context();
		ctx.setVariable("recipient", recipient);
		ctx.setVariable("validationLink", validationLink);

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

		try {
			message.setSubject("Email validation");
			message.setTo(recipient);

			// Create the HTML body using Thymeleaf
			String htmlContent = emailTemplateEngine.process("signupmail", ctx);
			message.setText(htmlContent, true /* isHtml */);
		} catch(MessagingException me) {
			me.printStackTrace();
		}
		javaMailSender.send(mimeMessage);
	}
}

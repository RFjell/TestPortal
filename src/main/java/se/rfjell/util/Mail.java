package se.rfjell.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mail {

//	@Autowired	
//	private JavaMailSender javaMailSender;

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
}

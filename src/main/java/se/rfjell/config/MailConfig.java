package se.rfjell.config;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailConfig {

	@Autowired
	private MailProperties mailProperties;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		Properties p = new Properties();
		for(Map.Entry<String,String> m : mailProperties.getProperties().entrySet()) {
			p.put(m.getKey(), m.getValue());
		}

		mailSender.setJavaMailProperties(p);
		mailSender.setHost(mailProperties.getHost());
		mailSender.setHost(mailProperties.getHost());
		mailSender.setPort(mailProperties.getPort());
		mailSender.setProtocol(mailProperties.getProtocol());
		mailSender.setUsername(mailProperties.getUsername());
		mailSender.setPassword(mailProperties.getPassword());

		return mailSender;
	}	
}

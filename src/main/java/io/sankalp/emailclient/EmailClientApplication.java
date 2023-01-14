package io.sankalp.emailclient;

import io.sankalp.emailclient.emails.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class EmailClientApplication {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(EmailClientApplication.class, args);
	}

	@EventListener ( ApplicationReadyEvent.class )
	public void triggerMail ( ) throws MessagingException {
		senderService.sendSimpleEmail( "arorasankalp.bp@gmail.com",
				"this is the email body",
				"This is the email subject");

		senderService.sendEmailWithAttachment( "arorasankalp.bp@gmail.com",
				"this is the new email body with attachment",
				"This email has attachment",
				"/Users/programmer/Documents/something.txt");
	}
}

package io.sankalp.emailclient.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail ( String toEmail,
                                  String body,
                                  String subject ) {

        SimpleMailMessage mailMessage = new SimpleMailMessage ();

        mailMessage.setFrom ( "sankalppop@gmail.com" );
        mailMessage.setTo ( toEmail );
        mailMessage.setText ( body );
        mailMessage.setSubject ( subject );

        mailSender.send ( mailMessage );
        System.out.println ( "Mail sent" );
    }

    public void sendEmailWithAttachment ( String toEmail,
                                          String body,
                                          String subject,
                                          String attachment ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage ();

        MimeMessageHelper helper = new MimeMessageHelper ( message, true );
        helper.setTo ( toEmail );
        helper.setText ( body );
        helper.setFrom ( "sankalppop@gmail.com" );
        helper.setSubject ( subject );

        FileSystemResource resource = new FileSystemResource ( new File( attachment ) );

        helper.addAttachment (Objects.requireNonNull(resource.getFilename()), resource );

        mailSender.send ( message );
        System.out.println ( "Message with attachment sent... " );
    }
}


package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.Email;
import com.itesm.fennec.domain.repository.EmailRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

@ApplicationScoped
public class EmailRepositoryImpl implements EmailRepository {

    @Override
    public void sendEmail(Email email) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dinogolderosas@gmail.com", "gxyajqhuhhfevrrq");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("dinogolderosas@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
        message.setSubject(email.getSubject());

        // Configurar el contenido HTML
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(email.getMessage(), "text/html");

        // Crear el multipart
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(htmlPart);

        // Establecer el contenido del mensaje como multipart
        message.setContent(multipart);

        Transport.send(message);
    }
}
package com.challenge.santander.service.impl;

import com.challenge.santander.model.MeetUpUserCreateDTO;
import com.challenge.santander.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Override
    public void sendMail(String destination, MeetUpUserCreateDTO meetUpUserCreateDTO) throws MessagingException {

        final String username = "santander.challenge.birras@gmail.com";
        final String password = "t1n18992";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("santander.challenge.birras@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("lethanvarg@gmail.com")
            );
            message.setSubject("MeetUp Pendiente");
            message.setText("Buenos días "+meetUpUserCreateDTO.getUserName()
                    + "\n\n Tienes una MeetUp el Día "+meetUpUserCreateDTO.getMeetUp().getMeetUpDate()+" En la dirección"
            +meetUpUserCreateDTO.getMeetUp().getDirection()
                    + "\n\n Saludos!! ");

            Transport.send(message);
            log.info("EMAIL SENT SUCCESSFULLY");
        }
}

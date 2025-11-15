package com.raja.emailservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raja.emailservice.dto.SendEmailDTO;
import com.raja.emailservice.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
@Slf4j
public class SendWelcomeEmailEventConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "sendWelcomeEmail" , groupId = "emailServiceGroup")
    public void handleSendWelcomeEmailEvent(String message) throws JsonProcessingException {
        SendEmailDTO emailDto = objectMapper.readValue(
                message,
                SendEmailDTO.class
        );

        String email = emailDto.getEmail();
        String subject = emailDto.getSubject();
        String body = emailDto.getBody();

        log.error("Processing Kafka event " + email + " with subject " + subject + " and body " + body);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "agrawalraja741@gmail.com",
                        "liiwvucxonxsfdnm");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtils.sendEmail(session, email, subject, body);
    }
}

package is1.order_app.service.email_sender_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    private final JavaMailSender mailSender;

    @Value("${email.sender.address}")
    private String senderEmailAddress;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String recipientEmailAddress, EmailSender mailType) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.senderEmailAddress);
        message.setTo(recipientEmailAddress);
        mailType.writeMessage(message);
        mailSender.send(message);
    }
}
package is1.order_app.service.mails_sevice;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    private final JavaMailSender mailSender;

	private final String senderEmailAddress = "orderappingsoftware@gmail.com";

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String recipientEmailAddress, EmailWriter mailType) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(this.senderEmailAddress);
        message.setTo(recipientEmailAddress);
        mailType.writeMessage(message);
        mailSender.send(message);
    }

    public void sendPassworChangedMail(String recipientEmailAddress) {
        this.sendMail(recipientEmailAddress, new PasswordChangeMailWriter());
    }

    public void sendOrderConfirmationMail(String recipientEmailAddress) {
        this.sendMail(recipientEmailAddress, new OrderConfirmationMailWriter());
    }

    public void sendOrderUpdateMailWriter(String recipientEmailAddress, String orderState, int orderId) {
        this.sendMail(recipientEmailAddress, new OrderUpdateMailWriter(orderState, orderId));
    }
    
}
package is1.order_app.service;

import is1.order_app.service.EmailSenderServiceMails.PasswordChangeMail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    private final JavaMailSender mailSender;

	private final String senderEmailAddress = "orderappingsoftware@gmail.com";

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String recipientEmailAddress, EmailSenderServiceMail mailType) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(this.senderEmailAddress);
        message.setTo(recipientEmailAddress);
        mailType.writeMessage(message);
        mailSender.send(message);
    }

    public void sendPassworChangedMail(String recipientEmailAddress) {
        this.sendMail(recipientEmailAddress, new PasswordChangeMail());
    }

    public void sendOrderConfirmationMail(String recipientEmailAddress) {
        this.sendMail(recipientEmailAddress, new OrderConfirmationMail());
    }

    public void actualizarPedido(String receptorMail, String estadoPedido, int IDpedido) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("orderappingsoftware@gmail.com");
        message.setTo(receptorMail);
        message.setSubject("Pedido " + estadoPedido);
        message.setText("Su pedido numero: " + IDpedido + " se encuentra" +
                 estadoPedido +
                "\nMuchas gracias por usar nuestro servicio");

        mailSender.send(message);
    }
}
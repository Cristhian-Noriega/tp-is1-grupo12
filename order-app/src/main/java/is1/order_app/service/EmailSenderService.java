package is1.order_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void restorePasswordMail(String receptorMail) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("orderappingsoftware@gmail.com");
        message.setTo(receptorMail);
        message.setSubject("Recuperacion de contrasena");
        message.setText("Su contrasena es:\n" +
                "******\n" +
                "Muchas gracias por usar nuestro servicio");

        mailSender.send(message);
    }
}
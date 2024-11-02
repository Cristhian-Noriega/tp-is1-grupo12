package is1.order_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    private JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void restorePasswordMail(String receptorMail) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("orderappingsoftware@gmail.com");
        message.setTo(receptorMail);
        message.setSubject("Recuperacion de contrasena");
        String link = "http://localhost:5173/#/password-recovery";
        message.setText("Recupere su contrasena con el siguiente link:\n" +
                link +
                "\nMuchas gracias por usar nuestro servicio");

        mailSender.send(message);
    }
}
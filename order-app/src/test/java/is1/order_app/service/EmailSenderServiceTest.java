package is1.order_app.service;

import is1.order_app.service.email_sender_service.EmailSenderService;
import is1.order_app.service.email_sender_service.PasswordChangeMail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {EmailSenderService.class})
@EnableAutoConfiguration
public class EmailSenderServiceTest {
    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    void testRestorePasswordMail() {
        String receptor = "ejemplo@gmail.com";
        emailSenderService.sendMail(receptor, new PasswordChangeMail());
    }
}

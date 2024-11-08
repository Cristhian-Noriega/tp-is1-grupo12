package is1.order_app.service;

import is1.order_app.service.mails_sevice.EmailSenderService;
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
        emailSenderService.sendPassworChangedMail(receptor);
    }
}

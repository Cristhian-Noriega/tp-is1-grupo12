package is1.order_app.service.mails_sevice;

import org.springframework.mail.SimpleMailMessage;

public class AccountVerificationMailWriter extends EmailWriter {
    
        @Override
        public void writeMessage(SimpleMailMessage message) {
            message.setSubject("Account Verification");
            message.setText("Dear Customer,\n\n"
                    + "Thank you for registering in our app. Please click on the following link to verify your account:\n\n"
                    + "http://localhost:5173/password/validation\n\n"
                    + "Best regards,\n"
                    + "Ing. de Software I - Grupo 12 :)");
        }

}

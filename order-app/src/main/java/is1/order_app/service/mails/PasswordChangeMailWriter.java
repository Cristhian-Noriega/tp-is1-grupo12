package is1.order_app.service;

import org.springframework.mail.SimpleMailMessage;

public class PasswordChangeMailWriter extends EmailWriter {

    protected String getSubject() {
        return "Cambio de contrasena";
    }

    protected String getTextContent() {
		String link = "http://localhost:5173/#/password-recovery";
        return "Confirme su cambio de contrasena usando el siguiente link:\n" + link;
    }
	
}
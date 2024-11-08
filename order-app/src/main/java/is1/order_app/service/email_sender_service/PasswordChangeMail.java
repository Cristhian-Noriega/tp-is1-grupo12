package is1.order_app.service.email_sender_service;

import org.springframework.mail.SimpleMailMessage;

public class PasswordChangeMail implements EmailSender {
	protected String subject = "Cambio de contrasena";
	protected String link = "http://localhost:5173/#/password-recovery";
	protected String textContent = "Confirme su cambio de contrasena usando el siguiente link:\n" + this.link;

	public void writeMessage(SimpleMailMessage message) {
		message.setSubject(this.subject);
		message.setText(this.textContent);
	}
}
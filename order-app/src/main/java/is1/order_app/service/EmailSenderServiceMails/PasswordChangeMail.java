package is1.order_app.service.EmailSenderServiceMails;

import org.springframework.mail.SimpleMailMessage;

public class PasswordChangeMail extends EmailSenderServiceMail {
	private String subject = "Cambio de contrasena";
	private String link = "http://localhost:5173/#/password-recovery";
	private String textContent = "COnfirme su cambio de contrasena usando el siguiente link:\n" + this.link;

	public void writeMessage(SimpleMailMessage message) {
		message.setSubject(this.subject);
		message.setText(this.textContent);
	}
}
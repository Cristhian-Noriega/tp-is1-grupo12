package is1.order_app.service.EmailSenderServiceMails;

import org.springframework.mail.SimpleMailMessage;

public class OrderConfirmationMail extends EmailSenderServiceMail {
	private String subject = "Orden confirmada";
	private String textContent = "Su pedido a sido confirmado.\n\nMuchas gracias por usar nuestro servicio";
	public void writeMessage(SimpleMailMessage message) {
		message.setSubject(this.subject);
		message.setText(this.textContent);
	}
}
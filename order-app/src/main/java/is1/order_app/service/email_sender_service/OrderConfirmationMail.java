package is1.order_app.service.email_sender_service;

import org.springframework.mail.SimpleMailMessage;

public class OrderConfirmationMail implements EmailSender {
	protected String subject = "Orden confirmada";
	protected String textContent = "Su pedido a sido confirmado.\n\nMuchas gracias por usar nuestro servicio";
	public void writeMessage(SimpleMailMessage message) {
		message.setSubject(this.subject);
		message.setText(this.textContent);
	}
}
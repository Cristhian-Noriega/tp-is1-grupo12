package is1.order_app.service.email_sender_service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSender {
	void writeMessage(SimpleMailMessage message);
}
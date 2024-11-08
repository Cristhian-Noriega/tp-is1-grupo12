package is1.order_app.service.mails_sevice;

import org.springframework.mail.SimpleMailMessage;

public class EmailWriter {

	protected String getSubject() {
		return "";
	};

	protected String getTextContent() {
		return "";
	};

	public void writeMessage(SimpleMailMessage message) {
		message.setSubject(this.getSubject());
		message.setText(this.getTextContent());
	}
	
}
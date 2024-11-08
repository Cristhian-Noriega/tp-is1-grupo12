package is1.order_app.service;

import org.springframework.mail.SimpleMailMessage;

public class OrderConfirmationMailWriter extends EmailWriter {

    protected String getSubject() {
        return "Orden confirmada";
    }

    protected String getTextContent() {
        return "Su pedido a sido confirmado.\n\nMuchas gracias por usar nuestro servicio.";
    }

}
package is1.order_app.service.mails_sevice;

import org.springframework.mail.SimpleMailMessage;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderItem;

public class OrderConfirmationMailWriter extends EmailWriter {

    private CustomerOrder order;

    public OrderConfirmationMailWriter(CustomerOrder order) {
        this.order = order;
    }

    @Override
    public void writeMessage(SimpleMailMessage message) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear Customer,\n\n")
                    .append("Your order has been confirmed. Here are the details:\n\n")
                    .append("Order ID: ").append(order.getId()).append("\n")
                    .append("Order Date: ").append(order.getConfirmationTime()).append("\n")
                    .append("Items:\n");

        for (OrderItem item : order.getItems()) {
            emailContent.append("- ").append(item.getProduct().getName())
                        .append(" (Qty: ").append(item.getQuantity());
        }

        emailContent.append("Thank you for your purchase!\n\n")
                    .append("Best regards,\n")
                    .append("Ing. de Software I - Grupo 12 :)");

        message.setSubject("Order Confirmation - Order #" + order.getId());
        message.setText(emailContent.toString());
    }

}
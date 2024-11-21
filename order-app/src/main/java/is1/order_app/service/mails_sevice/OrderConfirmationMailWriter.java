package is1.order_app.service.mails_sevice;

import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a");
        String formattedDate = order.getConfirmationTime().format(formatter);

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear Customer,\n\n")
                    .append("Your order has been confirmed. Here are the details:\n\n")
                    .append("Order ID: ").append(order.getId().toString()).append("\n")
                    .append("Order Date: ").append(formattedDate).append("\n")
                    .append("Items:\n");

        for (OrderItem item : order.getItems()) {
            emailContent.append("- ").append(item.getProduct().getName())
                        .append(" Quantity: ").append(item.getQuantity())
                        .append("\n");
        }

        emailContent.append("Thank you for your purchase!\n\n")
                    .append("Best regards,\n")
                    .append("Ing. de Software I - Grupo 12 :)");

        message.setSubject("Order Confirmation - Order #" + order.getId().toString());
        message.setText(emailContent.toString());
        message.setFrom("Ing. de Software I <orderappingsoftware@gmail.com>");
    }

}
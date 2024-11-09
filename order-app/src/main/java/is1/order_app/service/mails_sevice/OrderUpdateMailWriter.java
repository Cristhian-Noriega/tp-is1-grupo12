package is1.order_app.service.mails_sevice;

public class OrderUpdateMailWriter extends EmailWriter {

    private String orderState;
    private int orderId;

    public OrderUpdateMailWriter(String orderState, int orderId) {
        this.orderState = orderState;
        this.orderId = orderId;
    }

    public String getSubject() {
        return "El estado del pedido " + this.orderState + "a sido actualizado";
    }

    public String getTextContent() {
        return "Su pedido (numero: " + this.orderId + ") se encuentra" + this.orderState + "\n\nMuchas gracias por usar nuestro servicio";
    }

}
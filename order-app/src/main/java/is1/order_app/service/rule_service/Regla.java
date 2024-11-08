package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public interface Regla {
    boolean interpret(List<OrderItem> productos);
    String getMensajeError();
}

package is1.order_app.service.rule_service;

import is1.order_app.entities.Product;

import java.util.List;

public interface Regla {
    boolean interpret(List<Product> products, List<Integer> cantidades);
    String getMensajeError();
}

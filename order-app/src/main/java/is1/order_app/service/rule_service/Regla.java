package is1.order_app.service.rule_service;

import java.util.List;

public interface Regla {
    boolean interpret(List<is1.order_app.entities.product.Product> products, List<Integer> cantidades);
    String getMensajeError();
}

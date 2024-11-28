package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class MaxCantPorItemRegla implements Regla {
    private String msgError;
    private Integer valor;

    public MaxCantPorItemRegla(int valor, String mensajeError) {
        this.valor = valor;
        this.msgError = mensajeError;
    }

    @Override
    public boolean interpret(List<OrderItem> productos) {
        for (OrderItem item : productos) {
            if (item.getQuantity() > valor) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getMensajeError() {
        return this.msgError;
    }
}

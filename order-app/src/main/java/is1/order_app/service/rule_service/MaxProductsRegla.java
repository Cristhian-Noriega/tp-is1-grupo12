package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class MaxProductsRegla implements Regla {
    private int valor;
    private String msgError;

    public MaxProductsRegla(int valor, String msgError) {
        this.valor = valor;
        this.msgError = msgError;
    }

    @Override
    public boolean interpret(List<OrderItem> items) {
        int total = 0;
        for (OrderItem item : items) {
            total += item.getQuantity();
        }
        return total <= valor;
    }

    @Override
    public String getMensajeError() {
        return msgError;
    }
}

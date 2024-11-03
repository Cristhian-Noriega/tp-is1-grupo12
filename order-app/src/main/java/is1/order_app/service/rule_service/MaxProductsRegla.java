package is1.order_app.service.rule_service;

import is1.order_app.entities.product.Product;

import java.util.List;

public class MaxProductsRegla implements Regla {
    private int valor;
    private String msgError;

    public MaxProductsRegla(int valor, String msgError) {
        this.valor = valor;
        this.msgError = msgError;
    }

    @Override
    public boolean interpret(List<Product> products, List<Integer> cantidades) {
        int total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += cantidades.get(i);
        }
        return total <= valor;
    }

    @Override
    public String getMensajeError() {
        return msgError;
    }
}

package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;
import is1.order_app.entities.Product;

import java.util.List;

public class MinTotIntRegla implements Regla {
    private String atributo;
    private int valor;
    private String msgError;

    public MinTotIntRegla(String atributo, int valor, String msgError) {
        this.atributo = atributo;
        this.valor = valor;
        this.msgError = msgError;
    }

    @Override
    public boolean interpret(List<OrderItem> items) {
        Double total = 0.0;
        for (OrderItem item : items) {
            Object atributoValor = item.get(atributo);

            if (atributoValor instanceof Number) {
                double valorAtributo = ((Number) atributoValor).doubleValue();
                total += valorAtributo * item.getQuantity();
            } else {
                throw new IllegalArgumentException("El atributo '" + atributo + "' no es un valor entero.");
            }
        }
        return total >= valor;
    }

    @Override
    public String getMensajeError() {
        return msgError;
    }
}

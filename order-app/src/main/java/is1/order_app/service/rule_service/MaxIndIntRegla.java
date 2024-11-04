package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class MaxIndIntRegla implements Regla {
    private String atributo;
    private int valor;
    private String msgError;

    public MaxIndIntRegla(String atributo, int valor, String msgError) {
        this.atributo = atributo;
        this.valor = valor;
        this.msgError = msgError;
    }

    @Override
    public boolean interpret(List<OrderItem> items) {
        for (OrderItem item : items) {
            Object atributoValor = item.get(atributo);

            if (atributoValor instanceof Number) {
                double valorAtributo = ((Number) atributoValor).doubleValue();
                if (valorAtributo > valor) {
                    return false;
                }
            } else {
                throw new IllegalArgumentException("El atributo '" + atributo + "' no es un valor entero.");
            }
        }
        return true;
    }

    @Override
    public String getMensajeError() {
        return msgError;
    }
}

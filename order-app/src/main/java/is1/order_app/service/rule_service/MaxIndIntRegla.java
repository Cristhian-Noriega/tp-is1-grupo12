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
            double valorAtributo = Double.parseDouble((String) item.get(atributo));
            if (valorAtributo > valor) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getMensajeError() {
        return msgError;
    }
}

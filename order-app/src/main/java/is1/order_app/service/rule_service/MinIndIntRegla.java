package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class MinIndIntRegla implements Regla {
    private String atributo;
    private int valor;
    private String msgError;

    public MinIndIntRegla(String atributo, int valor, String msgError) {
        this.atributo = atributo;
        this.valor = valor;
        this.msgError = msgError;
    }

    @Override
    public boolean interpret(List<OrderItem> items) {
        for (OrderItem item : items) {
            Object atributoValor = item.get(atributo);
            if (atributoValor == null) {
                continue;
            }
            double valorAtributo = Double.parseDouble((String) atributoValor);
            if (valorAtributo < valor) {
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

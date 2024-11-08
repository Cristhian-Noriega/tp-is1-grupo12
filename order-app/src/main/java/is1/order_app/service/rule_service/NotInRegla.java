package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class NotInRegla implements Regla {
    private String atributo;
    private String valor_atributo;
    private String mensajeError;

    public NotInRegla(String atributo, String valor, String mensajeError) {
        this.atributo = atributo;
        this.valor_atributo = valor;
        this.mensajeError = mensajeError;
    }

    @Override
    public boolean interpret(List<OrderItem> items) {
        for (OrderItem item : items) {
            Object atributoValor = item.get(atributo);
            if (atributoValor == null) {
                continue;
            }
            if (atributoValor.equals(valor_atributo)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}

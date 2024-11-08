package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class NoMixRegla implements Regla {
    private String atributo;
    private String valor_1;
    private String valor_2;
    private String mensajeError;

    public NoMixRegla(String atributo, String valor_1, String valor_2, String mensajeError) {
        this.atributo = atributo;
        this.valor_1 = valor_1;
        this.valor_2 = valor_2;
        this.mensajeError = mensajeError;
    }

    @Override
    public boolean interpret(List<OrderItem> items) {
        boolean result_1 = false;
        boolean result_2 = false;
        for (OrderItem item : items) {
            Object atributoValor = item.get(atributo);
            if (atributoValor == null) {
                continue;
            }
            if (atributoValor.equals(valor_1)) {
                result_1 = true;
            } else if (atributoValor.equals(valor_2)) {
                result_2 = true;
            }
        }
        return !(result_1 && result_2);
    }

    @Override
    public String getMensajeError() {
        return this.mensajeError;
    }
}

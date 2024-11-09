package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class MaximoStringRegla implements Regla {
    private String atributo;
    private String valor;
    private int maximo;
    private String mensajeError;

    public MaximoStringRegla(String atributo, String valor, int maximo, String mensajeError) {
        this.atributo = atributo;
        this.valor = valor;
        this.maximo = maximo;
        this.mensajeError = mensajeError;
    }

    @Override
    public boolean interpret(List<OrderItem> items) {
        long total = 0;
        for (OrderItem item : items) {
            Object atributoValor = item.get(atributo);
            if (atributoValor == null) {
                continue;
            }
            if (valor.equals(atributoValor)) {
                total += item.getQuantity();
            }
        }
        return total <= maximo;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}

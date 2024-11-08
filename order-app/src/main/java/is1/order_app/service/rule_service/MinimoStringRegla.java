package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class MinimoStringRegla implements Regla {
    private String atributo;
    private String valor;
    private int minimo;
    private String mensajeError;

    public MinimoStringRegla(String atributo, String valor, int minimo, String mensajeError) {
        this.atributo = atributo;
        this.valor = valor;
        this.minimo = minimo;
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
        return total >= minimo;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}

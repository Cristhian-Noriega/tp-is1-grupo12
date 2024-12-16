package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class InMayorIntRegla implements Regla {
    private String atributo;
    private Integer valor_atributo;
    private String mensaje_error;

    public InMayorIntRegla(String atributo, Integer valor_atributo, String mensaje_error) {
        this.atributo = atributo;
        this.valor_atributo = valor_atributo;
        this.mensaje_error = mensaje_error;
    }

    @Override
    public boolean interpret(List<OrderItem> productos) {
        for (OrderItem item : productos) {
            Object atributoValor = item.get(atributo);
            if (atributoValor == null) {
                continue;
            }
            double valorAtributo = Double.parseDouble((String) atributoValor);
            if (valorAtributo > valor_atributo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getMensajeError() {
        return mensaje_error;
    }
}

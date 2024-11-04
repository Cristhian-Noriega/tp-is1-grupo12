package is1.order_app.service.rule_service;

import is1.order_app.entities.Product;

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
    public boolean interpret(List<Product> products, List<Integer> cantidades) {
        for (Product product : products) {
            if (product.get(atributo) == valor_atributo) {
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

package is1.order_app.service.rule_service;

import is1.order_app.entities.product.Product;

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
    public boolean interpret(List<Product> products, List<Integer> cantidades) {
        boolean result_1 = false;
        boolean result_2 = false;
        for (Product product : products) {
            if (product.get(atributo) == valor_1) {
                result_1 = true;
            } else if (product.get(atributo) == valor_2) {
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

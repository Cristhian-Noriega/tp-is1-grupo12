package is1.order_app.service.rule_service;

import is1.order_app.entities.product.Product;

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
    public boolean interpret(List<Product> products, List<Integer> cantidades) {
        for (Product product : products) {
            if (product.get(atributo) < valor) {
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

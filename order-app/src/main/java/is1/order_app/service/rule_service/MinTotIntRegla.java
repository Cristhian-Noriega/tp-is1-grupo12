package is1.order_app.service.rule_service;

import is1.order_app.entities.product.Product;

import java.util.List;

public class MinTotIntRegla implements Regla {
    private String atributo;
    private int valor;
    private String msgError;

    public MinTotIntRegla(String atributo, int valor, String msgError) {
        this.atributo = atributo;
        this.valor = valor;
        this.msgError = msgError;
    }

    @Override
    public boolean interpret(List<Product> products, List<Integer> cantidades) {
        int total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).get(atributo) * cantidades.get(i);
        }
        return total >= valor;
    }

    @Override
    public String getMensajeError() {
        return msgError;
    }
}

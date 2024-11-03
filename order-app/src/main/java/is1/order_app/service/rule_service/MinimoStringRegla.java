package is1.order_app.service.rule_service;

import is1.order_app.entities.product.Product;

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
    public boolean interpret(List<Product> products, List<Integer> cantidades) {
        long total = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).get(atributo) == valor) {
                total += cantidades.get(i);
            }
        }
        return total >= minimo;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}

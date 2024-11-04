package is1.order_app.service.rule_service;

import is1.order_app.entities.Product;

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
    public boolean interpret(List<Product> products, List<Integer> cantidades) {
        long total = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).get(atributo) == valor) {
                total += cantidades.get(i);
            }
        }
        return total <= maximo;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}

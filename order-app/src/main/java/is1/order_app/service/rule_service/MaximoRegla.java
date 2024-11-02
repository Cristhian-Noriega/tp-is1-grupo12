package is1.order_app.service.rule_service;

import java.util.List;

public class MaximoRegla implements Regla {
    private String atributo;
    private String valor;
    private int maximo;
    private String mensajeError;

    public MaximoRegla(String atributo, String valor, int maximo, String mensajeError) {
        this.atributo = atributo;
        this.valor = valor;
        this.maximo = maximo;
        this.mensajeError = mensajeError;
    }

    @Override
    public boolean interpret(List<is1.order_app.entities.product.Product> products, List<Integer> cantidades) {
        long total = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).get(atributo) == valor) {
                total += cantidades.get(i);
            }
        i
        return total <= maximo;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}

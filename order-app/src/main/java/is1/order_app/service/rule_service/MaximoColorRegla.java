package is1.order_app.service.rule_service;

import java.util.List;

public class MaximoColorRegla implements Regla {
    private String color;
    private int maximo;
    private String mensajeError;

    public MaximoColorRegla(String color, int maximo, String mensajeError) {
        this.color = color;
        this.maximo = maximo;
        this.mensajeError = mensajeError;
    }

    @Override
    public boolean interpret(List<is1.order_app.entities.product.Product> products, List<Integer> cantidades) {
        long total_color = 0;
        for (is1.order_app.entities.product.Product product : products) {
            if (true) {
                total_color++;
            }
        }
        return total_color <= maximo;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}

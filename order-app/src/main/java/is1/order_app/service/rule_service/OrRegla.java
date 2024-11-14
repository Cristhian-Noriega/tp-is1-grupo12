package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.List;

public class OrRegla implements Regla {
    private List<Regla> reglas;
    private String mensajeError;

    public OrRegla(List<Regla> reglas, String mensajeError) {
        this.reglas = reglas;
        this.mensajeError = mensajeError;
    }

    @Override
    public boolean interpret(List<OrderItem> productos) {
        if (reglas.isEmpty()) {
            return true;
        }
        for (Regla regla : reglas) {
            if (regla.interpret(productos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getMensajeError() {
        StringBuilder mensaje = new StringBuilder(mensajeError);
        for (Regla regla : reglas) {
            mensaje.append(regla.getMensajeError());
        }
        return mensaje.toString();
    }
}

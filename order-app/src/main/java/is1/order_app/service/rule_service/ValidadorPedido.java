package is1.order_app.service.rule_service;

import is1.order_app.entities.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPedido {
    private final List<Regla> reglas;

    public ValidadorPedido(String path) throws Exception {
        this.reglas = ReglaInterpreter.createReglas(path);
    }

    public List<String> validar(List<OrderItem> productos) {
        List<String> errors = new ArrayList<>();

        for (Regla regla : reglas) {
            if (!regla.interpret(productos)) {
                errors.add(regla.getMensajeError());
            }
        }

        return errors;
    }
}

package is1.order_app.service.rule_service;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPedido {
    private List<Regla> reglas;

    public ValidadorPedido(List<Regla> reglas) {
        this.reglas = reglas;
    }

    public List<String> validar(List<is1.order_app.entities.product.Product> productos, List<Integer> cantidades) {
        List<String> errors = new ArrayList<>();

        for (Regla regla : reglas) {
            if (!regla.interpret(productos, cantidades)) {
                errors.add(regla.getMensajeError());
            }
        }

        return errors;
    }
}

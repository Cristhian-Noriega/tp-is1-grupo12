package is1.order_app.service.rule_service;

import is1.order_app.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPedido {
    private final List<Regla> reglas;

    public ValidadorPedido(String path) throws Exception {
        this.reglas = ReglaInterpreter.createReglas(path);
    }

    public List<String> validar(List<Product> productos, List<Integer> cantidades) {
        List<String> errors = new ArrayList<>();

        for (Regla regla : reglas) {
            if (!regla.interpret(productos, cantidades)) {
                errors.add(regla.getMensajeError());
            }
        }

        return errors;
    }
}

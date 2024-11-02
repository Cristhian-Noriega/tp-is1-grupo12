package is1.order_app.service.rule_service;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReglaInterpreter {
    public static List<Regla> createReglas() throws Exception {
        List<Regla> reglas = new ArrayList<>();
        JSONArray jsonArray = new JSONArray("rules.json");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject regla = jsonArray.getJSONObject(i);
            String simbolo = regla.getString("regla");
            String atributo = regla.getString("atributo_afectado");
            String msjError = regla.getString("mensaje_error");

            if (simbolo == "<") {
                reglas.add(new MaximoRegla(atributo, regla.getString("valor_atributo"), regla.getInt("valor"), msjError));
            } else {
                throw new ClassNotFoundException("No hay regla con el simbolo y atributo proporcionados");
            }

        }

        return reglas;
    }
}

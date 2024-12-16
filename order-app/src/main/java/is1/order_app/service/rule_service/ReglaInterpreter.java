package is1.order_app.service.rule_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReglaInterpreter {
    public static List<Regla> createReglas(String path) throws Exception {
        List<Regla> reglas;
        String content = new String(Files.readAllBytes(Paths.get(path)));
        JSONArray jsonArray = new JSONArray(content);
        reglas = parseReglas(jsonArray);
        return reglas;
    }

    public static List<Regla> parseReglas(JSONArray jsonArray) throws Exception {
        List<Regla> reglas = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject regla = jsonArray.getJSONObject(i);
            String simbolo = regla.getString("regla");
            String msjError = regla.getString("mensaje_error");

            if (Objects.equals(simbolo, "or")) {
                JSONArray reglasInternas = regla.getJSONArray("reglas");
                List<Regla> orReglas = parseReglas(reglasInternas);
                OrRegla orRegla = new OrRegla(orReglas, msjError);
                reglas.add(orRegla);
                continue;
            } else if (Objects.equals(simbolo, "and")) {
                JSONArray reglasInternas = regla.getJSONArray("reglas");
                List<Regla> andReglas = parseReglas(reglasInternas);
                AndRegla andRegla = new AndRegla(andReglas, msjError);
                reglas.add(andRegla);
                continue;
            }

            if (Objects.equals(simbolo, "<=_str")) {
                reglas.add(new MaximoStringRegla(regla.getString("atributo_afectado"), regla.getString("valor_atributo"), regla.getInt("valor"), msjError));
            } else if (Objects.equals(simbolo, ">=_str")) {
                reglas.add(new MinimoStringRegla(regla.getString("atributo_afectado"), regla.getString("valor_atributo"), regla.getInt("valor"), msjError));
            } else if (Objects.equals(simbolo, "no_mix")) {
                reglas.add(new NoMixRegla(regla.getString("atributo_afectado"), regla.getString("valor_atributo"), regla.getString("valor_atributo2"), msjError));
            } else if (Objects.equals(simbolo, "<=_int")) {
                reglas.add(new MaxTotIntRegla(regla.getString("atributo_afectado"), regla.getInt("valor"), msjError));
            } else if (Objects.equals(simbolo, ">=_int")) {
                reglas.add(new MinTotIntRegla(regla.getString("atributo_afectado"), regla.getInt("valor"), msjError));
            } else if (Objects.equals(simbolo, "<_int")) {
                reglas.add(new MaxIndIntRegla(regla.getString("atributo_afectado"), regla.getInt("valor_atributo"), msjError));
            } else if (Objects.equals(simbolo, ">_int")) {
                reglas.add(new MinIndIntRegla(regla.getString("atributo_afectado"), regla.getInt("valor_atributo"), msjError));
            } else if (Objects.equals(simbolo, "not_in")) {
                reglas.add(new NotInRegla(regla.getString("atributo_afectado"), regla.getString("valor_atributo"), msjError));
            } else if (Objects.equals(simbolo, "min_products")) {
                reglas.add(new MinProductsRegla(regla.getInt("valor"), msjError));
            } else if (Objects.equals(simbolo, "max_products")) {
                reglas.add(new MaxProductsRegla(regla.getInt("valor"), msjError));
            } else if (Objects.equals(simbolo, "<item")) {
                reglas.add(new MaxCantPorItemRegla(regla.getInt("valor"), msjError));
            } else if (Objects.equals(simbolo, "in>_int")) {
                reglas.add(new InMayorIntRegla(regla.getString("atributo"), regla.getInt("valor_atributo"), msjError));
            } else {
                throw new ClassNotFoundException("No hay regla con el simbolo y atributo proporcionados");
            }
        }
        return reglas;
    }
}

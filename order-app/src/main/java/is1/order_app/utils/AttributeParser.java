package is1.order_app.utils;

import java.util.HashMap;
import java.util.Map;

public class AttributeParser {
    private final Map<String, String> typeMapping;

    public AttributeParser(TypeMappingLoader typeMappingLoader) {
        this.typeMapping = typeMappingLoader.loadTypeMapping();
    }

    public Map<String, Object> stringToMap(String str) {
        Map<String, Object> map = new HashMap<>();

        if (str == null || str.isEmpty()) return map;

        String[] pairs = str.split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split(": ");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                String type = typeMapping.get(key);
                if (type != null) {
                    switch (type) {
                        case "Integer":
                            map.put(key, Integer.valueOf(value));
                            break;
                        case "Double":
                            map.put(key, Double.valueOf(value));
                            break;
                        default:
                            map.put(key, value);
                    }
                }
            }
        }
        return map;
    }
}

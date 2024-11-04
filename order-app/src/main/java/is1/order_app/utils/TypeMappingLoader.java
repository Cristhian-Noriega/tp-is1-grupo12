package is1.order_app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TypeMappingLoader {
    private static final String FILE_NAME = "data_types_attributes.properties";

    public Map<String, String> loadTypeMapping() {
        Map<String, String> typeMapping = new HashMap<>();
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (input == null) {
                System.out.println("File not found: " + FILE_NAME);
                return null;
            }
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                typeMapping.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return typeMapping;
    }
}

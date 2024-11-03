package is1.order_app.mapper;

import is1.order_app.dto.ProductDTO;
import is1.order_app.dto.ProductViewDTO;
import is1.order_app.entities.Product;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setStock(productDTO.getStock());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        product.setProductExtraAtributtes(productDTO.getExtraAtributes());
        return product;
    }

    public ProductViewDTO toProductViewDTO(Product product) {
        ProductViewDTO response = new ProductViewDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setStock(product.getStock());
        response.setBrand(product.getBrand());
        response.setDescription(product.getDescription());
        response.setExtraAtributes(stringToMap(product.getProductExtraAtributtes()));
        return response;
    }
    private Map<String, String> loadTypeMapping() {
        Map<String, String> typeMapping = new HashMap<>();
        Properties properties = new Properties();
        String fileName= "data_types_atributes.properties";
        try (InputStream input = ProductMapper.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo: " + fileName);
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
    public Map<String, Object> stringToMap(String str) {
        Map<String, Object> map = new HashMap<>();
        Map<String, String> typeMapping=loadTypeMapping();

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

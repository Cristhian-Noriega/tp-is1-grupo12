package is1.order_app.entities;
import is1.order_app.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer stock;
    private String brand;
    private String description;
    private String productExtraAtributtes;

    public Product(String name, Integer stock, String brand, String description, Map<String, Object> productExtraAtributtes) {
        this.name = name;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.productExtraAtributtes = convertMapforDB(productExtraAtributtes);
    }
    public Product(){

    }
    private String convertMapforDB(Map<String, Object> productExtraAtributtes){
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : productExtraAtributtes.entrySet()) {
            result.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(", ");
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }

    public void setProductExtraAtributtes(Map<String, Object> productExtraAtributtes){
        this.productExtraAtributtes = convertMapforDB(productExtraAtributtes);
    }

    public Object get(String atributo) {
        switch (atributo) {
            case "name": return name;
            case "stock": return stock;
            case "brand": return brand;
            case "description": return description;
        }

        Map<String, Object> extraAttributesMap = parseProductExtraAttributes();
        return extraAttributesMap.getOrDefault(atributo, null);
    }

    private Map<String, Object> parseProductExtraAttributes() {
        Map<String, Object> attributesMap = new HashMap<>();

        if (productExtraAtributtes != null && !productExtraAtributtes.isEmpty()) {
            String[] entries = productExtraAtributtes.split(", ");

            for (String entry : entries) {
                String[] keyValue = entry.split(": ");
                if (keyValue.length == 2) {
                    attributesMap.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return attributesMap;
    }
    public void updateAllAtributes(ProductDTO productDTO){
        this.name=productDTO.getName();
        this.stock = productDTO.getStock();
        this.brand=productDTO.getBrand();
        this.description=productDTO.getDescription();
        this.productExtraAtributtes=convertMapforDB(productDTO.getExtraAtributes());
    }
}

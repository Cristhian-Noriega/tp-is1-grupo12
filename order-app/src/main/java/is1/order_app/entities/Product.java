package is1.order_app.entities;
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

}

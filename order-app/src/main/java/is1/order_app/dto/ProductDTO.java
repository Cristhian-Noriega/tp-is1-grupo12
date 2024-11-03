package is1.order_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ProductDTO {

    private String name;
    private Integer stock;
    private String brand;
    private String description;
    private Map<String,Object> extraAtributes;

    public ProductDTO(String name, Integer stock, String brand, String description, Map<String,Object> extraAtributes) {
        this.name = name;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.extraAtributes= extraAtributes;

    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", Extra='" + extraAtributes.toString() + '\'' +
                '}';
    }
}

package is1.order_app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Setter
@Getter
public class ProductViewDTO {
    private Long id;
    private String name;
    private Integer stock;
    private String brand;
    private String description;
    private Map<String,Object> extraAtributes;

    public ProductViewDTO(String name, Integer stock, String brand, String description, Map<String,Object> extraAtributes) {
        this.name = name;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.extraAtributes = extraAtributes;
    }
    public ProductViewDTO(){

    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", extraAtributes=" + extraAtributes +
                '}';
    }

}

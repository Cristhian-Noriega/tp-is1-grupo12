package is1.order_app.model.product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private String brand;
    private EnumCategory type;
    private String description;
    private String productData;

    public Product(String name, Double price, EnumCategory type, Integer stock, String brand, String description, String productData) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.productData = productData;
    }
    public Product(){

    }

}

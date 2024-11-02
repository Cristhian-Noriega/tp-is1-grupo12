package is1.order_app.dto;


import is1.order_app.entities.product.EnumCategory;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProductViewDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private String brand;
    private EnumCategory type;
    private String description;
    private Object productData;

    public ProductViewDTO(String name, Double price, EnumCategory type, Integer stock, String brand, String description, Object productData) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.productData = productData;
    }
    public ProductViewDTO(){

    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", productData=" + productData +
                '}';
    }

}

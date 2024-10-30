package is1.order_app.model.response;


import is1.order_app.model.product.EnumCategory;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private String brand;
    private EnumCategory type;
    private String description;
    private Object productData;

    public ProductResponse(String name, Double price, EnumCategory type, Integer stock, String brand, String description, Object productData) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.productData = productData;
    }
    public ProductResponse(){

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

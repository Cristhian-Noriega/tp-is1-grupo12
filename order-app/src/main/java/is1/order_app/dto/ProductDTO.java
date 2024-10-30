package is1.order_app.dto;

import is1.order_app.model.product.EnumCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private String name;
    private Double price;
    private EnumCategory type;
    private Integer stock;
    private String brand;
    private String description;
    private String color;
    private Double weight;
    private String expirationDate;
    private Boolean requiredRefrigeration;
    private String size;

    public ProductDTO(String name, Double price, EnumCategory type, Integer stock, String brand, String description, String color, Double weight, String expirationDate, Boolean requiredRefrigeration, String size) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.color = color;
        this.weight = weight;
        this.expirationDate = expirationDate;
        this.requiredRefrigeration = requiredRefrigeration;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", expirationDate='" + expirationDate + '\'' +
                ", requiereRefigeration=" + requiredRefrigeration +
                ", size='" + size + '\'' +
                '}';
    }
}

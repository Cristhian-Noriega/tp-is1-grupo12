package is1.order_app.model.request;

import is1.order_app.model.product.EnumCategory;

public class ProductRequest {

    private String name;
    private Double price;
    private EnumCategory type;
    private Integer stock;
    private String brand;
    private String description;
    private String color;
    private Double weight;
    private String expirationDate;
    private Boolean requiereRefigeration;
    private String size;

    public ProductRequest(String name, Double price, EnumCategory type, Integer stock, String brand, String description, String color, Double weight, String expirationDate, Boolean requiereRefigeration, String size) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
        this.brand = brand;
        this.description = description;
        this.color = color;
        this.weight = weight;
        this.expirationDate = expirationDate;
        this.requiereRefigeration = requiereRefigeration;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public EnumCategory getType() {
        return type;
    }

    public void setType(EnumCategory type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getRequiereRefigeration() {
        return requiereRefigeration;
    }

    public void setRequiereRefigeration(Boolean requiereRefigeration) {
        this.requiereRefigeration = requiereRefigeration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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
                ", requiereRefigeration=" + requiereRefigeration +
                ", size='" + size + '\'' +
                '}';
    }
}

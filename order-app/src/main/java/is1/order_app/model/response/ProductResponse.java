package is1.order_app.model.response;


import is1.order_app.model.product.EnumCategory;



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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EnumCategory getType() {
        return type;
    }

    public void setType(EnumCategory type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getProductData() {
        return productData;
    }

    public void setProductData(Object productData) {
        this.productData = productData;
    }
}

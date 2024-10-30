package is1.order_app.entities.product;

import lombok.Getter;

@Getter
public abstract class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String brand;
    private EnumCategory type;
    private String description;

    public boolean remove_stock(Integer num){
        // aca se podria tirar una excepcion
        if (stock - num < 0) {
            return false;
        }
        stock -= num;
        return true;
    }
    public void add_stock(Integer num){
        stock += num;
    }
}

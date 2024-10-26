package is1.order_app.entities.product;

import jdk.jfr.Description;

public abstract class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String brand;
    private EnumCategory type;
    private String description;

    public boolean remove_stock(Integer num){
        if (stock - num >= 0){
            stock -= num;
            return true;
        }else{
            return false;
        }
    }
    public void add_stock(Integer num){
        stock += num;
    }

    public int getStock() {
        return stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

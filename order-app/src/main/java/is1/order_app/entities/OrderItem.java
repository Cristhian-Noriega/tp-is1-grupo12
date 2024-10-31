package is1.order_app.entities;

import is1.order_app.model.product.Product;

public class OrderItem {
    private Long id;
    private Product product;
    private Integer quantity;

    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

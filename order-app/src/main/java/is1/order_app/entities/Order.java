package is1.order_app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    @Id
    private Long id;

    private User user;

    private List<OrderItem> items;

    private OrderState state;

    private Date creationDate;

    public Order() {}
}

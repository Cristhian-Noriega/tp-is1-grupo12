



package is1.order_app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private LocalDateTime confirmationTime;

    public CustomerOrder() {}

    @PrePersist
    public void initializeOrder() {
        if (confirmationTime == null) {
            this.confirmationTime = LocalDateTime.now();
        }
        if (state == null) {
            this.state = OrderState.CONFIRMED;
        }
    }

    public String getUserAdress() {
        return this.userId;
    }

    public boolean canBeCanceled() {
        return state == OrderState.CONFIRMED &&
                confirmationTime != null &&
                confirmationTime.plusHours(24).isAfter(LocalDateTime.now());
    }

}


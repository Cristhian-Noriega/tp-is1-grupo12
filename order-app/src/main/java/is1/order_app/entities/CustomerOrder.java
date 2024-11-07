package is1.order_app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import is1.order_app.service.EmailSenderService;

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
    public boolean initializeOrder() {
        if (confirmationTime == null || state == null) {
            return false;
        }
        this.confirmationTime = LocalDateTime.now();
        this.state = OrderState.CONFIRMED;
        return true;
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

package is1.order_app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private LocalDateTime creationDate;

    private LocalDateTime confirmationDate;

    public Order() {}

    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public boolean canBeCanceled() {
        return state != OrderState.PROCESSING &&
                confirmationDate != null &&
                confirmationDate.plusHours(24).isAfter(LocalDateTime.now());
    }
}

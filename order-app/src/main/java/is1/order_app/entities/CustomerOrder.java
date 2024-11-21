package is1.order_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @PrePersist
    public void initializeOrder() {
        if (confirmationTime == null) {
            this.confirmationTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")).toLocalDateTime();
        }
        if (state == null) {
            this.state = OrderState.CONFIRMED;
        }
    }


    public boolean canBeCanceled() {
        return state == OrderState.CONFIRMED &&
                confirmationTime != null &&
                confirmationTime.plusHours(24).isAfter(LocalDateTime.now());
    }

}



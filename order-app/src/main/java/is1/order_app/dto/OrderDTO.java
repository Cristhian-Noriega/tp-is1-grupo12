package is1.order_app.dto;

import is1.order_app.entities.OrderState;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String userId;
    private List<OrderItemDTO> items;
    private OrderState state;
    private LocalDateTime confirmationTime;
}


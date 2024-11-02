package is1.order_app.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String userId;
    private List<OrderItemDTO> items;
    private String state;
    private LocalDateTime creationDate;
    private LocalDateTime confirmationDate;
}


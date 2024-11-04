package is1.order_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private String userId;
    private List<OrderItemDTO> items;
}

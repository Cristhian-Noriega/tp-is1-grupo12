package is1.order_app.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
}

package is1.order_app.entities.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Warehouse {
    private Double weight;
    private String expirationDate;
    private Boolean requireRefrigeration;

}

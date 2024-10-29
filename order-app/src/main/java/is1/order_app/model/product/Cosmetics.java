package is1.order_app.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cosmetics {

    private String color;
    private Double weight;
    private String expirationDate;
    private Boolean requireRefrigeration;

}

package is1.order_app.dto;


import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductFilterDTO {
    @Nullable
    private String name;
    @Nullable
    private Integer stock;
    @Nullable
    private String brand;
    @Nullable
    private String description;
    @Nullable
    private String extraAtributes;
}


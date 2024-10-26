package is1.order_app.entities.product;

import java.util.function.LongFunction;
import org.springframework.lang.NonNull;

public record ProductCreateDTO(
        @NonNull String name,
        @NonNull String description,
        @NonNull EnumCategory category,
        long brandId
) {
    public Product asProduct() {
        return new Product(name, description);
    }
}


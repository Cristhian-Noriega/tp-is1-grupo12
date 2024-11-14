package is1.order_app.repository.specification;

import is1.order_app.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> name(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name != null && !name.isEmpty()) {
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
            return null;  // No aplicar filtro si el valor es null o vacío
        };
    }

    public static Specification<Product> brand(String brand) {
        return (root, query, criteriaBuilder) -> {
            if (brand != null && !brand.isEmpty()) {
                return criteriaBuilder.like(root.get("brand"), "%" + brand + "%");
            }
            return null;  // No aplicar filtro si el valor es null o vacío
        };
    }

    public static Specification<Product> stock(Integer stock) {
        return (root, query, criteriaBuilder) -> {
            if (stock != null) {
                return criteriaBuilder.equal(root.get("stock"), stock);
            }
            return null;  // No aplicar filtro si el valor es null o vacío
        };
    }

    public static Specification<Product> description(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description != null && !description.isEmpty()) {
                return criteriaBuilder.like(root.get("description"), "%" + description + "%");
            }
            return null;  // No aplicar filtro si el valor es null o vacío
        };
    }

    public static Specification<Product> attributes(String attributes) {
        return (root, query, criteriaBuilder) -> {
            if (attributes != null && !attributes.isEmpty()) {
                return criteriaBuilder.like(root.get("productExtraAtributtes"), "%" + attributes + "%");
            }
            return null;  // No aplicar filtro si el valor es null o vacío
        };
    }

}

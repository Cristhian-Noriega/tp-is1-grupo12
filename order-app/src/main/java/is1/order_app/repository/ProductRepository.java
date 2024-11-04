package is1.order_app.repository;

import is1.order_app.entities.product.EnumCategory;
import is1.order_app.entities.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    //aca usar jpa para la base de datos si la funcion no esta en crud repository
    List<Product> findByType(EnumCategory type);
}

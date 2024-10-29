package is1.order_app.repository;

import is1.order_app.model.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    //aca usar jpa para la base de datos.
}

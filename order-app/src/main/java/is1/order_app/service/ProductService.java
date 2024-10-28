package is1.order_app.service;

import is1.order_app.entities.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class  ProductService {

//    public Product createProduct(ProductCreateDTO data) {
//        var product = data.asProduct(brandRepository::getReferenceById);
//        return new Product(productRepository.save(product));
//    }
}


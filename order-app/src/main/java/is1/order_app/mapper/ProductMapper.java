package is1.order_app.mapper;

import is1.order_app.dto.ProductDTO;
import is1.order_app.dto.ProductViewDTO;
import is1.order_app.model.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setType(productDTO.getType());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        return product;
    }

    public ProductViewDTO toProductViewDTO(Product product) {
        ProductViewDTO response = new ProductViewDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setType(product.getType());
        response.setBrand(product.getBrand());
        response.setDescription(product.getDescription());
        return response;
    }
}

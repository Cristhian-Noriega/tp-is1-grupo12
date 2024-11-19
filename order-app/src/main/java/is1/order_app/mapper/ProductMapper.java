package is1.order_app.mapper;

import is1.order_app.dto.ProductDTO;
import is1.order_app.dto.ProductViewDTO;
import is1.order_app.entities.Product;
import is1.order_app.utils.AttributeParser;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final AttributeParser attributeParser;

    public ProductMapper(AttributeParser attributeParser) {
        this.attributeParser = attributeParser;
    }
    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setStock(productDTO.getStock());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        product.setProductExtraAtributtes(productDTO.getExtraAtributes());
        return product;
    }

    public ProductViewDTO toProductViewDTO(Product product) {
        ProductViewDTO response = new ProductViewDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setStock(product.getStock());
        response.setBrand(product.getBrand());
        response.setDescription(product.getDescription());
        response.setExtraAtributes(attributeParser.stringToMap(product.getProductExtraAtributtes()));
        return response;
    }
}

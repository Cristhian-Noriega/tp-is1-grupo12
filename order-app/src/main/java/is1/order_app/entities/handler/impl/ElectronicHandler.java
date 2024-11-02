package is1.order_app.entities.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import is1.order_app.entities.handler.ProductHandler;
import is1.order_app.entities.product.Electronic;
import is1.order_app.entities.product.EnumCategory;
import is1.order_app.entities.product.Product;
import is1.order_app.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElectronicHandler implements ProductHandler {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Boolean canHandle(EnumCategory category) {
        return category.equals(EnumCategory.ELECTRONICS);
    }

    @Override
    public String handleProduct(ProductDTO productRequest) throws JsonProcessingException {
        Electronic electronic=new Electronic(productRequest.getColor(),productRequest.getWeight());
        return objectMapper.writeValueAsString(electronic);
    }

    @Override
    public Object getProduct(Product product) throws JsonProcessingException {
        return objectMapper.readValue(product.getProductData(),Electronic.class);
    }
}

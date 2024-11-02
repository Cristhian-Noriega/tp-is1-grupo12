package is1.order_app.model.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import is1.order_app.entities.handler.ProductHandler;
import is1.order_app.entities.product.Clothing;
import is1.order_app.entities.product.EnumCategory;
import is1.order_app.entities.product.Product;
import is1.order_app.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClothingHandler implements ProductHandler {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Boolean canHandle(EnumCategory category) {
        return category.equals(EnumCategory.CLOTHING);
    }

    @Override
    public String handleProduct(ProductDTO productRequest) throws JsonProcessingException {
        Clothing clothing=new Clothing(productRequest.getSize(),productRequest.getColor());
        return objectMapper.writeValueAsString(clothing);
    }

    @Override
    public Object getProduct(Product product) throws JsonProcessingException {
        return objectMapper.readValue(product.getProductData(),Clothing.class);
    }
}

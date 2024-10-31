package is1.order_app.entities.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import is1.order_app.entities.handler.ProductHandler;
import is1.order_app.entities.product.Cosmetics;
import is1.order_app.entities.product.EnumCategory;
import is1.order_app.entities.product.Product;
import is1.order_app.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CosmeticsHandler implements ProductHandler{

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Boolean canHandle(EnumCategory category) {
        return category.equals(EnumCategory.COSMETICS);
    }

    @Override
    public String handleProduct(ProductDTO productRequest) throws JsonProcessingException {
        Cosmetics cosmetic=new Cosmetics(productRequest.getColor(),productRequest.getWeight(),productRequest.getExpirationDate(),productRequest.getRequiredRefrigeration());
        return objectMapper.writeValueAsString(cosmetic);
    }

    @Override
    public Object getProduct(Product product) throws JsonProcessingException {
        return objectMapper.readValue(product.getProductData(),Cosmetics.class);
    }
}

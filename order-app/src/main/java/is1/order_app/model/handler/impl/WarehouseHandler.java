package is1.order_app.model.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import is1.order_app.model.handler.ProductHandler;
import is1.order_app.model.product.EnumCategory;
import is1.order_app.model.product.Product;
import is1.order_app.model.product.Warehouse;
import is1.order_app.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseHandler implements ProductHandler {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Boolean canHandle(EnumCategory category) {
        return category.equals(EnumCategory.WAREHOUSE);
    }

    @Override
    public String handleProduct(ProductDTO productRequest) throws JsonProcessingException {
        Warehouse warehouse=new Warehouse(productRequest.getWeight(),productRequest.getExpirationDate(),productRequest.getRequiredRefrigeration());
        return objectMapper.writeValueAsString(warehouse);
    }

    @Override
    public Object getProduct(Product product) throws JsonProcessingException {
        return objectMapper.readValue(product.getProductData(),Warehouse.class);
    }
}

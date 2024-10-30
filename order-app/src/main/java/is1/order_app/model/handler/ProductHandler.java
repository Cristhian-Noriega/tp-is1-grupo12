package is1.order_app.model.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import is1.order_app.model.product.EnumCategory;
import is1.order_app.model.product.Product;
import is1.order_app.dto.ProductDTO;

public interface ProductHandler {
    public Boolean canHandle(EnumCategory category);
    public String handleProduct (ProductDTO productRequest) throws JsonProcessingException;
    public Object getProduct (Product product) throws JsonProcessingException;
}

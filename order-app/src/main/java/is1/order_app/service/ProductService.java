package is1.order_app.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import is1.order_app.exceptions.HandlerNotFoundException;
import is1.order_app.exceptions.ProductNotFoundException;
import is1.order_app.mapper.ProductMapper;
import is1.order_app.model.handler.ProductHandler;
import is1.order_app.model.product.EnumCategory;
import is1.order_app.model.product.Product;
import is1.order_app.dto.ProductDTO;
import is1.order_app.model.request.StockRequest;
import is1.order_app.dto.ProductViewDTO;
import is1.order_app.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProductService {
    private final List<ProductHandler> handlers;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(List<ProductHandler> handlers, ProductRepository productRepository, ProductMapper productMapper) {
        this.handlers = handlers;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductViewDTO createProduct(ProductDTO request) throws JsonProcessingException {
        ProductViewDTO response;
        ProductHandler handler = getHandler(request.getType());
        Product product = productMapper.toEntity(request);
        product.setProductData(handler.handleProduct(request));
        productRepository.save(product);
        response = productMapper.toProductViewDTO(product);
        response.setProductData(handler.getProduct(product));
        log.info("Producto creado con exito"+ response.toString());
        return response;
    }

    public ProductViewDTO updateProductStock(Long productId, StockRequest stockRequest) throws JsonProcessingException {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("The product with ID: "+ productId+ "does not exist");
        }

        Product product = productOpt.get();
        product.setStock(stockRequest.getNewStock());
        productRepository.save(product);

        return getProductResponse(product);
    }

    public void deleteProduct(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("The product with ID: " + productId + "does not exist");
        }

        productRepository.deleteById(productId);
    }

    public ProductViewDTO getProductById(Long productId) throws JsonProcessingException {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("The product with ID: " + productId + "does not exist");
        }

        return getProductResponse(productOpt.get());
    }

    public List<ProductViewDTO> getAllProducts() throws JsonProcessingException {
        List<ProductViewDTO> products = new ArrayList<>();
        for (Product p :productRepository.findAll()){
            products.add(getProductResponse(p));
        }
        return products;
    }


    private ProductViewDTO getProductResponse(Product product) throws JsonProcessingException {
        ProductViewDTO response;
        ProductHandler handler= getHandler(product.getType());
        response = productMapper.toProductViewDTO(product);
        response.setProductData(handler.getProduct(product));
        return response;
    }


    private ProductHandler getHandler(EnumCategory type) {
        for (ProductHandler handler : handlers) {
            if (handler.canHandle(type)) {
                return handler;
            }
        }
        throw new HandlerNotFoundException("No handler found for type " + type);
    }
}

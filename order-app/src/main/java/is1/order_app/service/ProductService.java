package is1.order_app.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import is1.order_app.exceptions.ProductNotFoundException;
import is1.order_app.model.handler.ProductHandler;
import is1.order_app.model.product.EnumCategory;
import is1.order_app.model.product.Product;
import is1.order_app.model.request.ProductRequest;
import is1.order_app.model.request.StockRequest;
import is1.order_app.model.response.ProductResponse;
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
    @Autowired
    private List<ProductHandler> handlers;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request) throws JsonProcessingException {
        ProductResponse response;
        ProductHandler handler= getHandler(request.getType());
        Product product=createGenericProduct(request);
        product.setProductData(handler.handleProduct(request));
        productRepository.save(product);
        response=createProductResponse(product);
        response.setProductData(handler.getProduct(product));
        log.info("Producto creado con exito"+ response.toString());
        return response;
    }

    public ProductResponse updateProductStock(Long productId, StockRequest stockRequest) throws JsonProcessingException, ProductNotFoundException {
        Optional<Product> productOpt = productRepository.findById(productId);
        if(productOpt.isPresent()){
            Product product = productOpt.get();
            product.setStock(stockRequest.getNewStock());
            productRepository.save(product);

            return getProductResponse(product);
        }
        throw new ProductNotFoundException("The product with ID: "+ productId+ "does not exist");

    }

    public void deleteProduct(Long productId) throws ProductNotFoundException {

        Optional<Product> productOpt = productRepository.findById(productId);
        if(productOpt.isPresent()){
            productRepository.deleteById(productId);
            return;
        }
        throw new ProductNotFoundException("The product with ID: "+ productId+ "does not exist");
    }

    public ProductResponse getProductById(Long productId) throws JsonProcessingException, ProductNotFoundException {

        Optional<Product> productOpt = productRepository.findById(productId);
        if(productOpt.isPresent()){
            return getProductResponse(productOpt.get());
        }
        throw new ProductNotFoundException("The product with ID: "+ productId+ "does not exist");
    }

    public List<ProductResponse> getAllProducts() throws JsonProcessingException {
        List<ProductResponse> products=new ArrayList<>();
        for (Product p :productRepository.findAll()){
            products.add(getProductResponse(p));
        }
        return products;
    }


    private ProductResponse getProductResponse(Product product) throws JsonProcessingException {
        ProductResponse response;
        ProductHandler handler= getHandler(product.getType());
        response=createProductResponse(product);
        response.setProductData(handler.getProduct(product));
        return response;
    }


    private ProductHandler getHandler(EnumCategory type) {
        for (ProductHandler handler : handlers) {
            if(handler.canHandle(type)) {
                return handler;
            }
        }
        throw new RuntimeException("No handler found for type " + type);
    }
    private Product createGenericProduct(ProductRequest productRequest){
        Product product=new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setType(productRequest.getType());
        product.setBrand(productRequest.getBrand());
        product.setDescription(productRequest.getDescription());
        return product;
    }

    private ProductResponse createProductResponse(Product product){
        ProductResponse response=new ProductResponse();
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

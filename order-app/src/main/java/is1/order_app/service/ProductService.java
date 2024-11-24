package is1.order_app.service;


import com.fasterxml.jackson.core.JsonProcessingException;

import is1.order_app.dto.ProductFilterDTO;
import is1.order_app.exceptions.ProductNotFoundException;
import is1.order_app.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import is1.order_app.entities.Product;
import is1.order_app.dto.ProductDTO;
import is1.order_app.dto.StockChangeDTO;
import is1.order_app.dto.ProductViewDTO;
import is1.order_app.repository.ProductRepository;
import is1.order_app.repository.specification.ProductSpecification;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private static final String DIVISOR = ",";

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductViewDTO createProduct(ProductDTO newProduct) throws JsonProcessingException {

        ProductViewDTO result;
        Product product = productMapper.toEntity(newProduct);
        productRepository.save(product);
        result = productMapper.toProductViewDTO(product);

        return result;
    }
    public ProductViewDTO updateProduct( Long productId, ProductDTO productDTO) throws JsonProcessingException {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("The product with ID: "+ productId+ " does not exist");
        }

        Product product = productOpt.get();
        product.updateAllAtributes(productDTO);
        productRepository.save(product);

        return getProduct(product);
    }
    public ProductViewDTO updateProductStock(Long productId, StockChangeDTO stockChangeDTO) throws JsonProcessingException {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("The product with ID: "+ productId+ " does not exist");
        }
        logger.info("llegue");
        Product product = productOpt.get();
        product.setStock(stockChangeDTO.getNewStock());
        productRepository.save(product);

        return getProduct(product);
    }

    public void deleteProduct(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("The product with ID: " + productId + " does not exist");
        }

        productRepository.deleteById(productId);
    }

    public ProductViewDTO getProductById(Long productId) throws JsonProcessingException {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("The product with ID: " + productId + " does not exist");
        }
        return getProduct(productOpt.get());
    }

    public List<ProductViewDTO> getAllProducts() throws JsonProcessingException {
        List<ProductViewDTO> products = new ArrayList<>();
        for (Product p :productRepository.findAll()){
            products.add(getProduct(p));
        }
        return products;
    }

    private ProductViewDTO getProduct(Product product) throws JsonProcessingException {
        ProductViewDTO response;
        response = productMapper.toProductViewDTO(product);
        return response;
    }
    public List<ProductViewDTO> getProductListFiltered(ProductFilterDTO productFilterDTO) throws JsonProcessingException {

        List<ProductViewDTO> response= new ArrayList<>();

        Specification<Product> spec = Specification.where(ProductSpecification.name(productFilterDTO.getName()))
                                                    .and(ProductSpecification.brand(productFilterDTO.getBrand()))
                                                    .and(ProductSpecification.stock(productFilterDTO.getStock()))
                                                    .and(ProductSpecification.description(productFilterDTO.getDescription()));

        if(productFilterDTO.getExtraAtributes()!= null && !productFilterDTO.getExtraAtributes().isEmpty()){
            List<String> atrs = List.of(productFilterDTO.getExtraAtributes().split(DIVISOR));
            for (String attribute : atrs) {
                spec.and(ProductSpecification.attributes(attribute));
            }
        }
        List<Product> products = productRepository.findAll(spec);

        for (Product p : products){
            response.add(getProduct(p));
        }

        return response;
    }
}

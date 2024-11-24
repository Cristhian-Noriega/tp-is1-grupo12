package is1.order_app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import is1.order_app.dto.*;
import is1.order_app.entities.Product;
import is1.order_app.exceptions.ProductNotFoundException;
import is1.order_app.mapper.ProductMapper;
import is1.order_app.repository.ProductRepository;
import is1.order_app.service.mails_sevice.EmailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private EmailSenderService emailSenderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() throws JsonProcessingException {

        Map<String, Object> extraAtributes = new HashMap<>();

        extraAtributes.put("color", "negro");
        extraAtributes.put("peso", "2 kg");


        ProductDTO productDTO = new ProductDTO("testing_product",10,"hitranchi","como pega papel con papel",extraAtributes);
        Product mockProduct = new Product();
        when(productMapper.toEntity(productDTO)).thenReturn(mockProduct);
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);
        when(productMapper.toProductViewDTO(mockProduct)).thenReturn(new ProductViewDTO());

        ProductViewDTO result = productService.createProduct(productDTO);

        // Assert
        verify(productMapper, times(1)).toEntity(productDTO);
        verify(productRepository, times(1)).save(mockProduct);
        verify(productMapper, times(1)).toProductViewDTO(mockProduct);
        assertNotNull(result);
    }

    @Test
    void getProductById() throws JsonProcessingException {
        Long orderId = 1L;
        Product mockProduct = new Product();
        ProductViewDTO mockProductViewDTO = new ProductViewDTO();

        when(productRepository.findById(orderId)).thenReturn(Optional.of(mockProduct));
        when(productMapper.toProductViewDTO(mockProduct)).thenReturn(mockProductViewDTO);

        ProductViewDTO result = productService.getProductById(1L);

        // Assert
        verify(productRepository, times(1)).findById(orderId);
        verify(productMapper, times(1)).toProductViewDTO(mockProduct);
        assertEquals(mockProductViewDTO, result);
    }

    @Test
    void notGetProductById() throws JsonProcessingException {
        Long productId = 1L;
        Product mockProduct = new Product();
        ProductViewDTO mockProductViewDTO = new ProductViewDTO();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        when(productMapper.toProductViewDTO(mockProduct)).thenReturn(mockProductViewDTO);

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(1L);
        });
        assertEquals("The product with ID: " + productId + " does not exist", exception.getMessage());

        // Assert
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    void deleteExistingProduct() throws JsonProcessingException {
        Long productId = 1L;

        Product mockProduct = new Product();
        mockProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        productService.deleteProduct(productId);
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);


    }
    @Test
    void deleteNotExistingProduct() throws JsonProcessingException {
        Long productId = 1L;

        Product mockProduct = new Product();
        mockProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(productId);
        });
        assertEquals("The product with ID: " + productId + " does not exist", exception.getMessage());
        verify(productRepository, times(1)).findById(productId);


    }
    @Test
    void updateStockNotExistingProduct() throws JsonProcessingException {
        Long productId = 1L;

        Product mockProduct = new Product();
        StockChangeDTO mockStockChangeDTO = new StockChangeDTO();
        mockProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProductStock(productId,mockStockChangeDTO);
        });
        assertEquals("The product with ID: " + productId + " does not exist", exception.getMessage());
        verify(productRepository, times(1)).findById(productId);


    }
    @Test
    void updateStockExistingProduct() throws JsonProcessingException {
        Long productId = 1L;

        Product mockProduct = new Product();
        StockChangeDTO mockStockChangeDTO = new StockChangeDTO();
        mockProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct) );

        productService.updateProductStock(productId,mockStockChangeDTO);

        verify(productRepository, times(1)).findById(productId);
        verify(productMapper, times(1)).toProductViewDTO(mockProduct);
        verify(productRepository, times(1)).save(mockProduct);
    }
    @Test
    void updateExistingProduct() throws JsonProcessingException {
        Long productId = 1L;

        Product mockProduct = new Product();

        Map<String, Object> extraAtributes = new HashMap<>();
        extraAtributes.put("color", "negro");
        extraAtributes.put("peso", "2 kg");

        ProductDTO mockUpdatedProductDto= new ProductDTO("testing",11,"hirtachi","for testing",extraAtributes);
        mockProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct) );


        productService.updateProduct(productId,mockUpdatedProductDto);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(mockProduct);
    }
    @Test
    void updateNotExistingProduct() throws JsonProcessingException {
        Long productId = 1L;

        Product mockProduct = new Product();
        mockProduct.setId(productId);
        Map<String, Object> extraAtributes = new HashMap<>();
        extraAtributes.put("color", "negro");
        extraAtributes.put("peso", "2 kg");
        ProductDTO mockUpdatedProductDto= new ProductDTO("testing",11,"hirtachi","for testing",extraAtributes);
        mockProduct.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProduct(productId,mockUpdatedProductDto);
        });
        assertEquals("The product with ID: " + productId + " does not exist", exception.getMessage());
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    void getAllProducts() throws JsonProcessingException {

        productService.getAllProducts();
        verify(productRepository, times(1)).findAll();

    }
    @Test
    void filterProducts() throws JsonProcessingException {
        ProductFilterDTO productFilterDTO = new ProductFilterDTO();
        productFilterDTO.setName("Product A");
        productFilterDTO.setBrand("Brand X");
        productFilterDTO.setStock(100);
        productFilterDTO.setDescription("Description of Product A");
        productFilterDTO.setExtraAtributes("color:red;size:M");

        Product product1 = new Product("Product A", 100, "Brand X", "Description of Product A", Map.of("color", "red", "size", "M"));
        Product product2 = new Product("Product B", 50, "Brand Y", "Description of Product B", Map.of("color", "blue", "size", "L"));

       when(productRepository.findAll(any(Specification.class))).thenReturn(List.of(product1, product2));

       List<ProductViewDTO> result = productService.getProductListFiltered(productFilterDTO);

       verify(productRepository, times(1)).findAll(any(Specification.class));
       assertNotNull(result);
       assertEquals(2, result.size());

    }


}



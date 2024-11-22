package is1.order_app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import is1.order_app.dto.*;
import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderItem;
import is1.order_app.entities.OrderState;
import is1.order_app.entities.Product;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.exceptions.ProductNotFoundException;
import is1.order_app.mapper.OrderMapper;
import is1.order_app.mapper.ProductMapper;
import is1.order_app.order_management.OrderCommandFactory;
import is1.order_app.repository.OrderRepository;
import is1.order_app.repository.ProductRepository;
import is1.order_app.service.mails_sevice.EmailSenderService;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
            ProductViewDTO result = productService.getProductById(1L);
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

        ProductViewDTO result = productService.updateProductStock(productId,mockStockChangeDTO);

        verify(productRepository, times(1)).findById(productId);
        verify(productMapper, times(1)).toProductViewDTO(mockProduct);
        verify(productRepository, times(1)).save(mockProduct);
    }

/*
    // Verifies that the service retrieves all orders associated with a specific user ID.
    @Test
    void getOrdersByUserId() {
        String userId = "user123";
        List<CustomerOrder> mockOrders = List.of(new CustomerOrder(), new CustomerOrder());
        when(productRepository.findByUserId(userId)).thenReturn(mockOrders);

        List<OrderDTO> result = productService.getOrdersByUserId(userId);

        // Assert
        verify(productRepository, times(1)).findByUserId(userId);
        assertEquals(mockOrders.size(), result.size());
    }


    // exception is thrown if there is insufficient stock for any product in the order.
    @Test
    void createOrder_not_stock() {
        // Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        CustomerOrder mockOrder = new CustomerOrder();
        OrderItem item = new OrderItem();
        Product product = new Product();
        product.setStock(0); // stock is 0
        item.setProduct(product);
        item.setQuantity(1);
        mockOrder.setItems(List.of(item));
        String email = "test@example.com";

        when(productMapper.toEntity(requestDTO, email)).thenReturn(mockOrder);

        // Assert
        assertThrows(IllegalStateException.class, () -> productService.createOrder(requestDTO, email));
        verify(productRepository, never()).save(any());
    }


    // Verifies that the service retrieves all orders in the CANCELED state.
    @Test
    void getCanceledOrders() {
        List<CustomerOrder> mockOrders = List.of(new CustomerOrder(), new CustomerOrder());
        when(productRepository.findByState(OrderState.CANCELED)).thenReturn(mockOrders);

        List<OrderDTO> result = productService.getCanceledOrders();

        // Assert
        verify(productRepository, times(1)).findByState(OrderState.CANCELED);
        assertEquals(mockOrders.size(), result.size());
    }


    @Test
    void executeCommand_shouldThrowExceptionForInvalidCommand() {
        // Arrange
        Long orderId = 1L;
        String invalidCommandName = "InvalidCommand";

        when(productRepository.findById(orderId)).thenReturn(Optional.of(new CustomerOrder()));

        // Assert
        assertThrows(IllegalArgumentException.class, () ->
                productService.executeCommand(orderId, invalidCommandName)
        );
    }

*/
}



package is1.order_app.service;

import is1.order_app.dto.OrderDTO;
import is1.order_app.dto.OrderRequestDTO;
import is1.order_app.entities.*;
import is1.order_app.exceptions.NotEnoughStockException;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.mapper.OrderMapper;
import is1.order_app.order_management.OrderCommandFactory;
import is1.order_app.repository.OrderRepository;
import is1.order_app.repository.ProductRepository;
import is1.order_app.service.mails_sevice.EmailSenderService;
import is1.order_app.service.rule_service.ValidadorPedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderCommandFactory orderCommandFactory;


    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private EmailSenderService emailSenderService;

    @Mock
    private ValidadorPedido validadorPedido;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // create and save order
    @Test
    void createOrder() {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        CustomerOrder mockOrder = new CustomerOrder();
        mockOrder.setItems(new ArrayList<>());
        String email = "test@example.com";

        when(orderMapper.toEntity(requestDTO, email)).thenReturn(mockOrder);
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);
        when(orderMapper.toDTO(mockOrder)).thenReturn(new OrderDTO());

        OrderDTO result = orderService.createOrder(requestDTO, email);

        // Assert
        verify(orderMapper, times(1)).toEntity(requestDTO, email);
        verify(orderRepository, times(1)).save(mockOrder);
        verify(orderMapper, times(1)).toDTO(mockOrder);
        assertNotNull(result);
    }

    // return order when found
    @Test
    void getOrderById() {
        Long orderId = 1L;
        CustomerOrder mockOrder = new CustomerOrder();
        OrderDTO mockOrderDTO = new OrderDTO();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(orderMapper.toDTO(mockOrder)).thenReturn(mockOrderDTO);

        OrderDTO result = orderService.getOrderById(orderId);

        // Assert
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderMapper, times(1)).toDTO(mockOrder);
        assertEquals(mockOrderDTO, result);
    }

    // throw Exception when not found
    @Test
    void getOrderById_Exception() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // assert
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
        verify(orderRepository, times(1)).findById(orderId);
    }

    // throw exception if user is not the owner
    @Test
    void cancelOrderByUser() {
        Long orderId = 1L;
        String userId = "user123";
        CustomerOrder mockOrder = new CustomerOrder();
        mockOrder.setUserId("otherUser");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        // Asert
        assertThrows(IllegalStateException.class, () -> orderService.cancelOrderByUser(orderId, userId));
        verify(orderRepository, times(1)).findById(orderId);
    }


     // Verifies that the service retrieves all orders associated with a specific user ID.
    @Test
    void getOrdersByUserId() {
        String userId = "user123";
        List<CustomerOrder> mockOrders = List.of(new CustomerOrder(), new CustomerOrder());
        when(orderRepository.findByUserId(userId)).thenReturn(mockOrders);

        List<OrderDTO> result = orderService.getOrdersByUserId(userId);

        // Assert
        verify(orderRepository, times(1)).findByUserId(userId);
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

        when(orderMapper.toEntity(requestDTO, email)).thenReturn(mockOrder);

        // Assert
        assertThrows(NotEnoughStockException.class, () -> orderService.createOrder(requestDTO, email));
        verify(productRepository, never()).save(any());
    }


    // Verifies that the service retrieves all orders in the CANCELED state.
    @Test
    void getCanceledOrders() {
        List<CustomerOrder> mockOrders = List.of(new CustomerOrder(), new CustomerOrder());
        when(orderRepository.findByState(OrderState.CANCELED)).thenReturn(mockOrders);

        List<OrderDTO> result = orderService.getCanceledOrders();

        // Assert
        verify(orderRepository, times(1)).findByState(OrderState.CANCELED);
        assertEquals(mockOrders.size(), result.size());
    }


    @Test
    void executeCommand_shouldThrowExceptionForInvalidCommand() {
        // Arrange
        Long orderId = 1L;
        String invalidCommandName = "InvalidCommand";

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(new CustomerOrder()));

        // Assert
        assertThrows(IllegalArgumentException.class, () ->
                orderService.executeCommand(orderId, invalidCommandName)
        );
    }


}

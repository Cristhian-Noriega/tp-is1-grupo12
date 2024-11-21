package is1.order_app.service;

import is1.order_app.dto.OrderCommandDTO;
import is1.order_app.order_management.command.CancelOrderCommand;
import is1.order_app.repository.ProductRepository;
import is1.order_app.service.rule_service.ValidadorPedido;
import is1.order_app.dto.OrderDTO;
import is1.order_app.entities.OrderItem;
import is1.order_app.entities.OrderState;
import is1.order_app.entities.Product;
import is1.order_app.mapper.OrderMapper;
import is1.order_app.order_management.command.OrderCommand;
import is1.order_app.order_management.OrderCommandFactory;
import is1.order_app.dto.OrderRequestDTO;
import is1.order_app.entities.CustomerOrder;
import is1.order_app.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import is1.order_app.service.mails_sevice.EmailSenderService;
import is1.order_app.exceptions.NotEnoughStockException;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.exceptions.OrderValidatorErrorsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final EmailSenderService emailSenderService;
    private final ValidadorPedido validadorPedido;

    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, EmailSenderService emailSenderService, ValidadorPedido validadorPedido, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.emailSenderService = emailSenderService;
        this.validadorPedido = validadorPedido;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderDTO createOrder(OrderRequestDTO orderRequestDTO, String email) {
        CustomerOrder order = orderMapper.toEntity(orderRequestDTO, email);

        this.validateRules(order);

        // Verificar y reducir el stock de los productos en la orden
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();

            if (product.getStock() < item.getQuantity()) {
                throw new NotEnoughStockException("There is not enough stock for the product: " + product.getName());
            }
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product); // Guardar los cambios en el producto
        }

        order = orderRepository.save(order);
        this.sendOrderConfirmationMail(email, order);

        return orderMapper.toDTO(order);
    }


    @Transactional
    public void executeCommand(Long orderId, String commandName) {
        CustomerOrder order = findOrderById(orderId);
        OrderCommand command = OrderCommandFactory.createCommand(commandName);

        command.execute(order);
        if (command instanceof CancelOrderCommand) {
            updateStockForCanceledOrder(order);
        }

        orderRepository.save(order);
    }



    public List<OrderCommandDTO> getAvailableCommands(Long orderId) {
        OrderDTO orderDTO = getOrderById(orderId);
        CustomerOrder order = orderMapper.toEntity(orderDTO);
        return OrderCommandFactory.getAvailableCommands(order);
    }

    public OrderDTO getOrderById(Long id) {
        return orderMapper.toDTO(findOrderById(id));
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orders = new ArrayList<>();
        for (CustomerOrder order : orderRepository.findAll()) {
            orders.add(orderMapper.toDTO(order));
        }
        return orders;
    }

    private CustomerOrder findOrderById(Long id) {
        Optional<CustomerOrder> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("The order with id " + id  + " not exists");
        }
        return order.get();
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("The order with id " + id + " not exists");
        }
        orderRepository.deleteById(id);
    }

    public void validateRules(CustomerOrder order) {
        List<String> listaDeErrores = this.validadorPedido.validar(order.getItems());
        if (!(listaDeErrores.isEmpty())) {
            throw new OrderValidatorErrorsException(listaDeErrores);
        }
    }

    public void sendOrderConfirmationMail(String recipientEmailAddress, CustomerOrder order) {
        emailSenderService.sendOrderConfirmationMail(recipientEmailAddress, order);
    }

    public List<OrderDTO> getOrdersByUserId(String userId) {
        List<CustomerOrder> orders = orderRepository.findByUserId(userId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (CustomerOrder order : orders) {
            orderDTOS.add(orderMapper.toDTO(order));
        }
        return orderDTOS;
    }

    public List<OrderDTO> getCanceledOrders() {
        return orderRepository.findByState(OrderState.CANCELED)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> getProcessingOrders() {
        return orderRepository.findByState(OrderState.PROCESSING)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> getSentOrders() {
        return orderRepository.findByState(OrderState.SENT)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> getConfirmedOrders() {
        return orderRepository.findByState(OrderState.CONFIRMED)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> getCanceledOrdersByUserId(String userId) {
        return orderRepository.findByUserIdAndState(userId, OrderState.CANCELED)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> getProcessingOrdersByUserId(String userId) {
        return orderRepository.findByUserIdAndState(userId, OrderState.PROCESSING)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    public List<OrderDTO> getSentOrdersByUserId(String userId) {
        return orderRepository.findByUserIdAndState(userId, OrderState.SENT)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Transactional
    public void cancelOrderByUser(Long orderId, String userId) {
        CustomerOrder order = findOrderById(orderId);

        if (!order.getUserId().equals(userId)) {
            throw new IllegalStateException("You are not authorized to cancel this order.");
        }

        executeCommand(orderId, "CancelOrderCommand");
    }


    public List<OrderDTO> getConfirmedOrdersByUserId(String userId) {
        return orderRepository.findByUserIdAndState(userId, OrderState.CONFIRMED)
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Transactional
    private void updateStockForCanceledOrder(CustomerOrder order) {
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            int newStock = product.getStock() + item.getQuantity();

            product.setStock(newStock);
            productRepository.save(product);
        }
    }



}


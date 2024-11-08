package is1.order_app.service;

import is1.order_app.dto.OrderCommandDTO;
import is1.order_app.service.rule_service.ValidadorPedido;
import is1.order_app.dto.OrderDTO;
import is1.order_app.mapper.OrderMapper;
import is1.order_app.order_management.command.OrderCommand;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.order_management.OrderCommandFactory;
import is1.order_app.dto.OrderRequestDTO;
import is1.order_app.entities.CustomerOrder;
import is1.order_app.repository.OrderRepository;
import is1.order_app.service.mails_sevice.EmailSenderService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final EmailSenderService emailSenderService;
    private ValidadorPedido validadorPedido;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, EmailSenderService emailSenderService, ValidadorPedido validadorPedido) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.emailSenderService = emailSenderService;
        try {
            this.validadorPedido = new ValidadorPedido("src/main/resources/rules.json");
        } catch (Exception e) {}
    }

    @Transactional
    public OrderDTO createOrder(OrderRequestDTO orderRequestDTO) {
        CustomerOrder order = orderMapper.toEntity(orderRequestDTO);
        this.confirmOrder(order);
        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Transactional
    public void executeCommand(Long orderId, String commandName) {
        CustomerOrder order = findOrderById(orderId);
        OrderCommand command = OrderCommandFactory.createCommand(commandName);
        command.execute(order);
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

    public boolean confirmOrder(CustomerOrder order) {
        List<String> listaDeErrores = this.validadorPedido.validar(order.getItems());
        if (!(listaDeErrores.isEmpty())) {
            return false;
        }

        String email = order.getUserAdress();
        this.emailSenderService.sendOrderConfirmationMail(email);
        return true;
    }

    public List<OrderDTO> getOrdersByUserId(String userId) {
        List<CustomerOrder> orders = orderRepository.findByUserId(userId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (CustomerOrder order : orders) {
            orderDTOS.add(orderMapper.toDTO(order));
        }
        return orderDTOS;
    }
    
}


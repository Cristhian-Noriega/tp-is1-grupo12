package is1.order_app.service;

import is1.order_app.dto.OrderDTO;
import is1.order_app.mapper.OrderMapper;
import is1.order_app.order_management.command.OrderCommand;
import is1.order_app.entities.Order;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.order_management.OrderCommandFactory;
import is1.order_app.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Optional<Order> searchOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("The order with id " + id + " does not exist"));
    }

    public void executeCommand(Long orderId, OrderCommand command) {
        Order order = getOrderById(orderId);
        command.execute(order);
        orderRepository.save(order);
    }

    public List<OrderCommand> getAvailableCommands(Long orderId) {
        Order order = getOrderById(orderId);
        return OrderCommandFactory.getAvailableCommands(order);
    }

    private Order findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("The order with id " + id  + "not exists");
        }
        return order.get();
    }

    public List<OrderDTO> getAllOrdersdto() {
        List<OrderDTO> orders = new ArrayList<>();
        for (Order order :orderRepository.findAll()){
            orders.add(OrderMapper.toDTO(order));
        }
        return orders;
    }

    public OrderDTO getOrderByIddto(Long id) {
        return OrderMapper.toDTO(findOrderById(id));
    }
}

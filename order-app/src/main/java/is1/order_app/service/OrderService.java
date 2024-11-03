package is1.order_app.service;

import is1.order_app.dto.OrderDTO;
import is1.order_app.entities.Order;
import is1.order_app.entities.OrderState;
import is1.order_app.exceptions.CannotCancelOrderException;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.mapper.OrderMapper;
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

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orders = new ArrayList<>();
        for (Order order :orderRepository.findAll()){
            orders.add(OrderMapper.toDTO(order));
        }
        return orders;
    }

    public OrderDTO getOrderById(Long id) {
        return OrderMapper.toDTO(findOrderById(id));
    }

    public void cancelOrder(Long id) {
        Order order = findOrderById(id);
        if (!order.canBeCanceled()) {
            throw new CannotCancelOrderException("The order cannot be cancelled");
        }
       order.setState(OrderState.CANCELED);
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("The order with id " + id + " not exists");
        }
        orderRepository.deleteById(id);
    }

    public void confirmOrder(Long id) {
        Order order = findOrderById(id);
        order.setState(OrderState.CONFIRMED);
        orderRepository.save(order);
    }

    private Order findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("The order with id " + id  + "not exists");
        }
        return order.get();
    }
}

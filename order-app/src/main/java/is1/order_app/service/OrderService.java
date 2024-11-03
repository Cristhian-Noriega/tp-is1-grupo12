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

    public List<Order> getAllOrders() {
        List<Order> products = new ArrayList<>();
        for (Order order :orderRepository.findAll()){
            products.add(order);
        }
        return products;
    }

    public Optional<Order> searchOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderDTO getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("The order with id " + id  + "not exists");
        }
        return OrderMapper.toDTO(order.get());
    }

    public void cancelOrder(Long id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException("The order with id " + id  + "not exists");
        }

        Order order = orderOpt.get();
        if (!order.canBeCanceled()) {
            throw new CannotCancelOrderException("The order cannot be cancelled");
        }
       order.setState(OrderState.CANCELED);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public void completeOrder(Long id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new OrderNotFoundException("The order with id " + id  + "not exists");
        }

        Order order = orderOpt.get();
        order.setState(OrderState.CONFIRMED);
    }

}

package is1.order_app.mapper;

import is1.order_app.dto.OrderDTO;
import is1.order_app.dto.OrderItemDTO;
import is1.order_app.dto.OrderRequestDTO;
import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderItem;
import is1.order_app.entities.Product;
import is1.order_app.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public static ProductRepository productRepository;

    public OrderMapper(ProductRepository productRepository) {
        OrderMapper.productRepository = productRepository;
    }
    public static OrderDTO toDTO(CustomerOrder order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setItems(order.getItems().stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList())
        );
        orderDTO.setState(order.getState());
        orderDTO.setConfirmationTime(order.getConfirmationTime());

        return orderDTO;
    }

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(orderItem.getProduct().getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());

        return orderItemDTO;
    }

    public static CustomerOrder toEntity(OrderRequestDTO orderRequestDTO) {
        CustomerOrder order = new CustomerOrder();
        order.setUserId(orderRequestDTO.getUserId());
        List<OrderItem> orderItems = orderRequestDTO.getItems().stream()
                .map(itemDTO -> {
                    OrderItem item = toEntity(itemDTO);
                    item.setOrder(order);
                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(orderItems);

        return order;
    }

    public static CustomerOrder toEntity(OrderDTO orderDTO) {
        CustomerOrder order = new CustomerOrder();
        order.setId(orderDTO.getId());
        order.setUserId(orderDTO.getUserId());

        List<OrderItem> orderItems = orderDTO.getItems().stream()
                .map(OrderMapper::toEntity)
                .collect(Collectors.toList());
        order.setItems(orderItems);

        order.setState(orderDTO.getState());
        order.setConfirmationTime(orderDTO.getConfirmationTime());

        return order;
    }

    public static OrderItem toEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        orderItem.setProduct(product);

        orderItem.setQuantity(orderItemDTO.getQuantity());

        return orderItem;
    }
}

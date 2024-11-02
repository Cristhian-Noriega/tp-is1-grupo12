package is1.order_app.mapper;

import is1.order_app.dto.OrderDTO;
import is1.order_app.dto.OrderItemDTO;
import is1.order_app.entities.Order;
import is1.order_app.entities.OrderItem;
import is1.order_app.entities.OrderState;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setItems(order.getItems().stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList())
        );
        orderDTO.setState(order.getState().toString());
        orderDTO.setConfirmationDate(order.getConfirmationTime());
        return orderDTO;
    }

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(orderItem.getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());

        return orderItemDTO;
    }

    public static Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUserId(orderDTO.getUserId());

        List<OrderItem> orderItems = orderDTO.getItems().stream()
                .map(OrderMapper::toEntity)
                .collect(Collectors.toList());
        order.setItems(orderItems);

        order.setState(OrderState.valueOf(orderDTO.getState()));
        order.setConfirmationTime(orderDTO.getConfirmationDate());
        return order;

    }

    public static OrderItem toEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItem.getId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItem;
    }
}

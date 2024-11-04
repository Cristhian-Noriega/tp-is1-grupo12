package is1.order_app.controller;

import is1.order_app.dto.OrderDTO;
import is1.order_app.exceptions.CannotCancelOrderException;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.mapper.OrderMapper;
import is1.order_app.order_management.command.OrderCommand;
import is1.order_app.entities.Order;
import is1.order_app.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

@PostMapping("/createOrder")
public ResponseEntity<Order> createOrder(@RequestBody Order order) {
    Order newOrder = orderService.createOrder(order);
    return ResponseEntity.ok(newOrder);
}

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrdersdto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderByIddto(id));
    }

    @PostMapping("/{orderId}/executeCommand")
    public ResponseEntity<String> executeCommand(@PathVariable Long orderId, @RequestBody OrderCommand command) {
        try {
            orderService.executeCommand(orderId, command);
            return ResponseEntity.ok("Command executed successfully.");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        } catch (CannotCancelOrderException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/{orderId}/availableCommands")
    public ResponseEntity<List<OrderCommand>> getAvailableCommands(@PathVariable Long orderId) {
        List<OrderCommand> commands = orderService.getAvailableCommands(orderId);
        return ResponseEntity.ok(commands);
    }
}

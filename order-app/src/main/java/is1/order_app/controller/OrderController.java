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
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

//    @PostMapping("/createOrder")
//    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
//        Order order = orderMapper.toEntity(orderDTO);
//        Order newOrder = orderService.createOrder(order);
//        OrderDTO responseDTO = orderMapper.toDTO(newOrder);
//        return ResponseEntity.ok(responseDTO);
//    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        // Verificación temporal
        System.out.println("Order to be created: " + order);

        Order newOrder = orderService.createOrder(order);
        OrderDTO responseDTO = orderMapper.toDTO(newOrder);
        return ResponseEntity.ok(responseDTO);
    }



    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getOrder")
    public ResponseEntity<Order> searchOrderById(@PathVariable Long orderId) {
        Optional<Order> order = orderService.searchOrderById(orderId);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

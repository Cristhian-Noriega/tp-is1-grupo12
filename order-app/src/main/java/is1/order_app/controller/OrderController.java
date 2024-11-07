package is1.order_app.controller;

import is1.order_app.dto.OrderCommandDTO;
import is1.order_app.dto.OrderDTO;
import is1.order_app.dto.OrderRequestDTO;
import is1.order_app.exceptions.CannotCancelOrderException;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            OrderDTO orderDTO = orderService.createOrder(orderRequestDTO);
            return ResponseEntity.ok(orderDTO);
        } catch (Exception e) {
            e.printStackTrace(); // Log the stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @PostMapping("/{orderId}/executeCommand")
    public ResponseEntity<String> executeCommand(@PathVariable Long orderId, @RequestBody OrderCommandDTO commandDTO) {
        try {
            orderService.executeCommand(orderId, commandDTO.getCommandName());
            return ResponseEntity.ok("Command executed successfully.");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        } catch (CannotCancelOrderException | IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/{orderId}/availableCommands")
    public ResponseEntity<List<OrderCommandDTO>> getAvailableCommands(@PathVariable Long orderId) {
        List<OrderCommandDTO> commands = orderService.getAvailableCommands(orderId);
        return ResponseEntity.ok(commands);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

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
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            orderService.createOrder(orderRequestDTO);
            return ResponseEntity.ok("Your order has been successfully created.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.getOrderById(id));
    }
    
    @PostMapping("/{orderId}/executeCommand")
    public ResponseEntity<String> executeCommand(@PathVariable Long orderId, @RequestBody OrderCommandDTO commandDTO) {
        try {
            this.orderService.executeCommand(orderId, commandDTO.getCommandName());
            return ResponseEntity.ok("Command executed successfully.");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        } catch (CannotCancelOrderException | IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/{orderId}/availableCommands")
    public ResponseEntity<List<OrderCommandDTO>> getAvailableCommands(@PathVariable Long orderId) {
        List<OrderCommandDTO> commands = this.orderService.getAvailableCommands(orderId);
        return ResponseEntity.ok(commands);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/canceled")
    public ResponseEntity<List<OrderDTO>> getCanceledOrders() {
        List<OrderDTO> orders = orderService.getCanceledOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/processing")
    public ResponseEntity<List<OrderDTO>> getProcessingOrders() {
        List<OrderDTO> orders = orderService.getProcessingOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/sent")
    public ResponseEntity<List<OrderDTO>> getSentOrders() {
        List<OrderDTO> orders = orderService.getSentOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/confirmed")
    public ResponseEntity<List<OrderDTO>> getConfirmedOrders() {
        List<OrderDTO> orders = orderService.getConfirmedOrders();
        return ResponseEntity.ok(orders);
    }

    //Endpoints para usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@PathVariable String userId) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}/canceled")
    public ResponseEntity<List<OrderDTO>> getCanceledOrdersByUserId(@PathVariable String userId) {
        List<OrderDTO> orders = orderService.getCanceledOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}/processing")
    public ResponseEntity<List<OrderDTO>> getProcessingOrdersByUserId(@PathVariable String userId) {
        List<OrderDTO> orders = orderService.getProcessingOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}/sent")
    public ResponseEntity<List<OrderDTO>> getSentOrdersByUserId(@PathVariable String userId) {
        List<OrderDTO> orders = orderService.getSentOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}/confirmed")
    public ResponseEntity<List<OrderDTO>> getConfirmedOrdersByUserId(@PathVariable String userId) {
        List<OrderDTO> orders = orderService.getConfirmedOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{orderId}/cancelByUser")
    public ResponseEntity<String> cancelOrderByUser(@PathVariable Long orderId, @RequestParam String userId) {
        try {
            orderService.cancelOrderByUser(orderId, userId);
            return ResponseEntity.ok("Your order has been successfully canceled.");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        } catch (CannotCancelOrderException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}

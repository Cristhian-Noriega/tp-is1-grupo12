package is1.order_app.controller;

import is1.order_app.entities.Order;
import is1.order_app.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
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

    @GetMapping("/cancelOrder")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        try {
            orderService.cancelOrder(orderId);
        } catch(OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order couldn't be canceled because the order with that ID wasn't found.");
        } catch(CannotCancelOrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order couldn't be canceled because 24 hours have already passed.");
        }
        return ResponseEntity.ok("order canceled");        
    }

    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completeOrder")
    public ResponseEntity<String> completeOrder(@PathVariable Long orderId) {
        try {
            orderService.completeOrder(orderId);
        } catch(OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order couldn't be confirmed because the order with that ID wasn't found.");
        }
        return ResponseEntity.ok("order completed");
    }
}

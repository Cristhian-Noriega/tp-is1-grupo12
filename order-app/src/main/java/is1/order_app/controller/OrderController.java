package is1.order_app.controller;

import is1.order_app.dto.OrderCommandDTO;
import is1.order_app.dto.OrderDTO;
import is1.order_app.dto.OrderRequestDTO;
import is1.order_app.exceptions.CannotCancelOrderException;
import is1.order_app.exceptions.OrderNotFoundException;
import is1.order_app.security.JwtUserDetails;
import is1.order_app.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO,
            @AuthenticationPrincipal JwtUserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        OrderDTO orderDTO = orderService.createOrder(orderRequestDTO, userDetails.email());
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.getOrderById(id));
    }

    @PostMapping("/{orderId}/executeCommand")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderCommandDTO>> getAvailableCommands(@PathVariable Long orderId) {
        List<OrderCommandDTO> commands = this.orderService.getAvailableCommands(orderId);
        return ResponseEntity.ok(commands);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully.");
    }

    @GetMapping("satus/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable String status) {
        List<OrderDTO> orders;
        switch (status) {
            case "canceled":
                orders = orderService.getCanceledOrders();
                break;
            case "processing":
                orders = orderService.getProcessingOrders();
                break;
            case "sent":
                orders = orderService.getSentOrders();
                break;
            case "confirmed":
                orders = orderService.getConfirmedOrders();
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(orders);
    }

    // Endpoints para usuario
    @GetMapping("/user")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@AuthenticationPrincipal JwtUserDetails userDetails) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userDetails.email());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable String status,
            @AuthenticationPrincipal JwtUserDetails userDetails) {
        List<OrderDTO> orders;
        switch (status) {
            case "canceled":

                orders = orderService.getCanceledOrdersByUserId(userDetails.email());
                break;

            case "processing":

                orders = orderService.getProcessingOrdersByUserId(userDetails.email());
                break;

            case "sent":
                orders = orderService.getSentOrdersByUserId(userDetails.email());
                break;
            case "confirmed":
                orders = orderService.getConfirmedOrdersByUserId(userDetails.email());
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{orderId}/cancelByUser")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> cancelOrderByUser(@PathVariable Long orderId,
            @AuthenticationPrincipal JwtUserDetails userDetails) {
        try {
            orderService.cancelOrderByUser(orderId, userDetails.email());
            return ResponseEntity.ok("Your order has been successfully canceled.");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        } catch (CannotCancelOrderException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}

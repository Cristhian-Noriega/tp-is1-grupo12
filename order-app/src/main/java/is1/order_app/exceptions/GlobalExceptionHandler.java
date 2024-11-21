package is1.order_app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(OrderValidatorErrorsException.class)
    public ResponseEntity<Object> handleOrderValidatorErrorsException(OrderValidatorErrorsException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_ACCEPTABLE.value());
        body.put("message", "Order is not valid");
        body.put("errors", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("message", "An unexpected error occurred");
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<Object> handleNotEnoughStockException(NotEnoughStockException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("message", "Not enough stock for the product");
        body.put("errors", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}

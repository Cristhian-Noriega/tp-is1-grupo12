package is1.order_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Cannot cancel order")
public class CannotCancelOrderException extends RuntimeException{
    public CannotCancelOrderException(String message) {
        super(message);
    }
}

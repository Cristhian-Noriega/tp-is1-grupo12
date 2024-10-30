package is1.order_app.exceptions;

import is1.order_app.model.product.EnumCategory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HandlerNotFoundException extends RuntimeException{
    public HandlerNotFoundException(String message) {
        super(message);
    }
}

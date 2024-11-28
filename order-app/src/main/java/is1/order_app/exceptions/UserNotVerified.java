package is1.order_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User not verified")
public class UserNotVerified extends RuntimeException{ 
    public UserNotVerified(String message) {
        super(message);
    }
}

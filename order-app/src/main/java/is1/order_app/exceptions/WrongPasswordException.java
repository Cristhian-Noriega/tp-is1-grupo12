package is1.order_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Wrong password")
public class WrongPasswordException extends RuntimeException {
	public WrongPasswordException(String message) {
		super(message);
	}
}

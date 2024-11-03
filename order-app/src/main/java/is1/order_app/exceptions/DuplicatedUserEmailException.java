package is1.order_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicated user email")
public class DuplicatedUserEmailException extends RuntimeException{
	public DuplicatedUserEmailException(String message) {
		super(message);
	}
}

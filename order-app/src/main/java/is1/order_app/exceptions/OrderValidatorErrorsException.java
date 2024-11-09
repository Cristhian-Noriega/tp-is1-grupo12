package is1.order_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Order is not valid")
public class OrderValidatorErrorsException extends RuntimeException {

    private List<String> listaDeErrores;

	public OrderValidatorErrorsException(List<String> listaDeErrores) {
		this.listaDeErrores = listaDeErrores;
	}
    public String getMessage() {
        return "La orden no es valida debido a los siguientes motivos:\n" + String.join("\n• ", this.listaDeErrores);
    }
}

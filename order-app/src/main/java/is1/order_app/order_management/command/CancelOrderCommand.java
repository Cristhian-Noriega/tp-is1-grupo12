package is1.order_app.order_management.command;

import is1.order_app.entities.Order;
import is1.order_app.entities.OrderState;
import is1.order_app.exceptions.CannotCancelOrderException;

public class CancelOrderCommand implements OrderCommand {
    @Override
    public void execute(Order order) {
        if (!order.canBeCanceled()) {
            throw new CannotCancelOrderException("The order cannot be canceled.");
        }
        order.setState(OrderState.CANCELED);
    }
}

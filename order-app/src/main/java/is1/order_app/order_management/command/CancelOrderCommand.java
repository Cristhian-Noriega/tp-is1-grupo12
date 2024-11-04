package is1.order_app.order_management.command;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;
import is1.order_app.exceptions.CannotCancelOrderException;

public class CancelOrderCommand implements OrderCommand {
    @Override
    public void execute(CustomerOrder order) {
        if (!order.canBeCanceled()) {
            throw new CannotCancelOrderException("The order cannot be canceled.");
        }
        order.setState(OrderState.CANCELED);
    }
}

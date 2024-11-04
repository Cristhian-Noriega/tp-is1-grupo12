package is1.order_app.order_management.command;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;

public class ShipOrderCommand implements OrderCommand {
    @Override
    public void execute(CustomerOrder order) {
        if (order.getState() == OrderState.PROCESSING) {
            order.setState(OrderState.SENT);
        } else {
            throw new IllegalStateException("Order cannot be shipped from current state.");
        }
    }
}

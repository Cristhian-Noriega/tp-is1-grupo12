package is1.order_app.order_management.command;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;

public class ProcessingOrderCommand implements OrderCommand {
    @Override
    public void execute(CustomerOrder order) {
        if (order.getState() == OrderState.CONFIRMED) {
            order.setState(OrderState.PROCESSING);
        } else {
            throw new IllegalStateException("Order cannot pass to processing from current state.");
        }
    }
}

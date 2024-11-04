package is1.order_app.order_management.command;

import is1.order_app.entities.Order;

public interface OrderCommand {
    void execute(Order order);
}

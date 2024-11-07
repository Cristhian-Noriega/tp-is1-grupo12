package is1.order_app.order_management.command;


import is1.order_app.entities.CustomerOrder;

public interface OrderCommand {
    void execute(CustomerOrder order);
}

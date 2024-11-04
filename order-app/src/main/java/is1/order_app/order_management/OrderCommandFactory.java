package is1.order_app.order_management;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;
import is1.order_app.order_management.command.CancelOrderCommand;
import is1.order_app.order_management.command.CompleteOrderCommand;
import is1.order_app.order_management.command.OrderCommand;
import is1.order_app.order_management.command.ShipOrderCommand;

import java.util.ArrayList;
import java.util.List;

public class OrderCommandFactory {
    public static List<OrderCommand> getAvailableCommands(CustomerOrder order) {
        List<OrderCommand> commands = new ArrayList<>();
        OrderState state = order.getState();

        switch (state) {
            case CONFIRMED:
                commands.add(new CompleteOrderCommand());
                commands.add(new CancelOrderCommand());
                break;
            case PROCESSING:
                commands.add(new ShipOrderCommand());
                break;
            default:
                break;
        }

        return commands;
    }
}

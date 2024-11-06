package is1.order_app.order_management;

import is1.order_app.dto.OrderCommandDTO;
import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;
import is1.order_app.order_management.command.CancelOrderCommand;
import is1.order_app.order_management.command.OrderCommand;
import is1.order_app.order_management.command.ProcessingOrderCommand;
import is1.order_app.order_management.command.SentOrderCommand;


import java.util.ArrayList;
import java.util.List;
public class OrderCommandFactory {
    public static List<OrderCommandDTO> getAvailableCommands(CustomerOrder order) {
        List<OrderCommandDTO> commands = new ArrayList<>();
        OrderState state = order.getState();

        switch (state) {
            case CONFIRMED:
                commands.add(new OrderCommandDTO("ProcessingOrderCommand"));
                commands.add(new OrderCommandDTO("CancelOrderCommand"));
                break;
            case PROCESSING:
                commands.add(new OrderCommandDTO("SentOrderCommand"));
                break;
            default:
                break;
        }

        return commands;
    }

    public static OrderCommand createCommand(String commandName) {
        switch (commandName) {
            case "ProcessingOrderCommand":
                return new ProcessingOrderCommand();
            case "CancelOrderCommand":
                return new CancelOrderCommand();
            case "SentOrderCommand":
                return new SentOrderCommand();
            default:
                throw new IllegalArgumentException("Invalid command name: " + commandName);
        }
    }
}

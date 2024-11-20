package is1.order_app.order_management.command;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingOrderCommandTest {

    @Test
    void execute_SetOrderToProcessing_whenStateIsConfirmed() {
        CustomerOrder order = new CustomerOrder();
        order.setState(OrderState.CONFIRMED);

        ProcessingOrderCommand command = new ProcessingOrderCommand();

        command.execute(order);

        // Assert
        assertEquals(OrderState.PROCESSING, order.getState());
    }

    @Test
    void execute_Exception_whenStateIsInvalid() {
        CustomerOrder order = new CustomerOrder();
        order.setState(OrderState.SENT); // Estado no permitido

        ProcessingOrderCommand command = new ProcessingOrderCommand();

        // Assert
        assertThrows(IllegalStateException.class, () -> command.execute(order));
    }
}

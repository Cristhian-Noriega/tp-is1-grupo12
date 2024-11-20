package is1.order_app.order_management.command;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SentOrderCommandTest {

    @Test
    void execute_SetOrderToSent_whenStateIsProcessing() {
        CustomerOrder order = new CustomerOrder();
        order.setState(OrderState.PROCESSING);

        SentOrderCommand command = new SentOrderCommand();

        command.execute(order);

        // Assert
        assertEquals(OrderState.SENT, order.getState());
    }

    @Test
    void execute_Exception_whenStateIsNotProcessing() {
        CustomerOrder order = new CustomerOrder();
        order.setState(OrderState.CONFIRMED); // Estado no permitido

        SentOrderCommand command = new SentOrderCommand();

        // Assert
        assertThrows(IllegalStateException.class, () -> command.execute(order));
    }

    @Test
    void execute_Exception_whenStateIsAlreadySent() {
        CustomerOrder order = new CustomerOrder();
        order.setState(OrderState.SENT); // Estado no permitido

        SentOrderCommand command = new SentOrderCommand();

        // Assert
        assertThrows(IllegalStateException.class, () -> command.execute(order));
    }
}

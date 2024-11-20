package is1.order_app.order_management.command;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;
import is1.order_app.exceptions.CannotCancelOrderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CancelOrderCommandTest {

    @Test
    void execute_CancelOrder_whenOrderCanBeCanceled() {
        CustomerOrder order = mock(CustomerOrder.class);
        when(order.canBeCanceled()).thenReturn(true); //porque depende de CurtomerOrder

        CancelOrderCommand command = new CancelOrderCommand();
        command.execute(order);

        // Assert
        verify(order, times(1)).setState(OrderState.CANCELED);
    }

    @Test
    void execute_Exception_whenOrderCannotBeCanceled() {
        CustomerOrder order = new CustomerOrder();
        order.setState(OrderState.SENT); // Estado que no permite cancelación

        CancelOrderCommand command = new CancelOrderCommand();

        //Assert
        assertThrows(CannotCancelOrderException.class, () -> command.execute(order));
    }

    @Test
    void execute_Exception_whenOrderCannotBeCanceled_two() {
        CustomerOrder order = new CustomerOrder();
        order.setState(OrderState.PROCESSING); // Estado que no permite cancelación

        CancelOrderCommand command = new CancelOrderCommand();

        //Assert
        assertThrows(CannotCancelOrderException.class, () -> command.execute(order));
    }
}

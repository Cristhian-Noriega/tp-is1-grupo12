package is1.order_app.repository;

import is1.order_app.entities.CustomerOrder;
import is1.order_app.entities.OrderState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByUserId(String userId);
    List<CustomerOrder> findByState(OrderState state);
    List<CustomerOrder> findByUserIdAndState(String userId, OrderState state);
}


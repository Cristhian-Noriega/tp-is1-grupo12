import './productsSelectionPage.css'
import { UserLayout } from '../../components/user_layout/UserLayout';
import { ConfirmedOrdersList } from '../../components/orders/user_confirmed_orders/ConfirmedOrdersList';
import userOrdersService from '../../services/userOrders';
import { userOrdersUtils } from '../../utils/userOrdersUtils';
import { Context } from '../../context/Context';
import CardMessage from "../../components/card_message/CardMessagge";
import { useContext, useEffect } from 'react';
import { OrderStatusFilter } from '../../components/order_status_filter/OrderStatusFilter';

export const UserOrdersPage = () => {
  const { getUserFromLocalStorage, user, setShowMessage, showMessage} = useContext(Context);
  const { orders, setOrders, cancelOrder, setOriginalOrders } = userOrdersUtils();

  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  useEffect(() => {
    if (user.token) { 
    userOrdersService.setToken(user.token)
    userOrdersService.getByUserId().then((orders) => {
      setOriginalOrders(orders);
      setOrders(orders);
    });
    
}}, [user]); 

  const handleCancelOrder = (orderId) => {
    console.log(`id de la orden: ${orderId}`);
    const confirmed = window.confirm("¿Estas seguro de que deseas cancelar la orden?");
    if (confirmed) {
      cancelOrder(orderId);
    }
  };

  return (
    <div className='user-confirmed-orders-wrapper'>
      <UserLayout />
      <div className='table-content-wrapper'>
        <h2>Órdenes de Usuario</h2>
        <OrderStatusFilter />
        <ConfirmedOrdersList orders={orders} handleCancelOrder={handleCancelOrder} />
      </div>
      {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
  );
};

import './productsSelectionPage.css'
import { UserLayout } from '../../components/user_layout/UserLayout';
import { ConfirmedOrdersList } from '../../components/orders/user_confirmed_orders/ConfirmedOrdersList';
import userOrdersService from '../../services/userOrders';
import { userOrdersUtils } from '../../utils/userOrdersUtils';
import { Context } from '../../context/Context';

import { useContext, useEffect, useState } from 'react';
import { OrderStatusFilter } from '../../components/order_status_filter/OrderStatusFilter';

export const UserOrdersPage = () => {
  const { getUserFromLocalStorage, user} = useContext(Context);
  const { orders, setOrders, cancelOrder, setOriginalOrders } = userOrdersUtils();

  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  useEffect(() => {
    //if (user && user.id) { Por el momento no funciona porque user.id es null
    userOrdersService.getByUserId(user.id).then((orders) => {
      setOriginalOrders(orders);
      setOrders(orders);
    });
  }, []); // Este paréntesis de cierre fue corregido aquí

  const handleCancelOrder = (orderId) => {
    console.log("Handle cancel order");
    console.log(`id de la orden: ${orderId}`);
    cancelOrder(user.id, orderId);
    console.log("orden cancelada");
    
  };

  return (
    <div className='user-confirmed-orders-wrapper'>
      <UserLayout />
      <div className='table-content-wrapper'>
        <h2>Órdenes de Usuario</h2>
        <OrderStatusFilter />
        <ConfirmedOrdersList orders={orders} handleCancelOrder={handleCancelOrder} />
      </div>
    </div>
  );
};

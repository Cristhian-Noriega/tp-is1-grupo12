import './productsSelectionPage.css'
import { UserLayout } from '../../components/user_layout/UserLayout';
import { ConfirmedOrdersList } from '../../components/orders/user_confirmed_orders/ConfirmedOrdersList';
import userOrdersService from '../../services/userOrders';
import { userOrdersUtils } from '../../utils/userOrdersUtils';
import { Context } from '../../context/Context';

import { useContext, useEffect, useState } from 'react';
import { OrderStatusFilter } from '../../components/order_status_filter/OrderStatusFilter';

export const UserOrdersPage = () => {
  const { getUserFromLocalStorage } = useContext(Context);
  const { orders, setOrders, cancelOrder, setOriginalOrders } = userOrdersUtils();

  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  useEffect(() => {
    //if (user && user.id) { Por el momento no funciona porque user.id es null
    userOrdersService.getByUserId("lucas.ezequiel.321@gmail.com").then((orders) => {
      setOriginalOrders(orders);
      setOrders(orders);
    });
  }, []); // Este paréntesis de cierre fue corregido aquí

  const handleCancelOrder = (orderId) => {
    console.log("AAAAAA");
    console.log(orderId);
    const response = cancelOrder(orderId);
    console.log("orden cancelada");
    console.log(response);
  };

  return (
    <div className='user-confirmed-orders-wrapper'>
      <UserLayout />
      <div className='table-content-wrapper'>
        <h2>Órdenes de Usuario</h2>
        <OrderStatusFilter />
        <ConfirmedOrdersList orders={orders} handleDeleteOrder={handleCancelOrder} />
      </div>
    </div>
  );
};

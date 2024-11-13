import './productsSelectionPage.css'
import { UserLayout } from '../../components/user_layout/UserLayout';
import { ConfirmedOrdersList } from '../../components/orders/user_confirmed_orders/ConfirmedOrdersList';
import userOrdersService from '../../services/userOrders'
import {userOrdersUtils} from '../../utils/userOrdersUtils'
import { Context} from '../../context/Context';

import { useContext, useEffect, useState } from 'react';

export const UserOrdersPage = () => {

  const { user, setUser } = useContext(Context);
  const { orders,setOrders, deleteOrder} = userOrdersUtils();


    useEffect(() => {
      const loggedUserJSON = window.localStorage.getItem("loggedAppUser");
      if (loggedUserJSON) {
        const user = JSON.parse(loggedUserJSON);
        setUser(user);
        userOrdersService.setToken(user)
      }
    }, []);

    useEffect(() => {
      //if (user && user.id) { Por el momento no funciona porque user.id es null
        userOrdersService.getByUserId(user.id).then((orders) => setOrders(orders));
    //  }
    }, []);
    
    const handleDeleteOrder = (orderId) => {
      console.log("orden cancelada")
      console.log(`order id : ${orderId}`)
      deleteOrder(orderId)
  }
    
  return (
    <div className='user-confirmed-orders-wrapper'>
    <UserLayout/>
      <div className='table-content-wrapper'>
        <h2 >Órdenes de Usuario</h2>
        <ConfirmedOrdersList orders={orders} handleDeleteOrder={handleDeleteOrder}/>
      </div>
    </div>
  );
};


import './productsSelectionPage.css'
import { UserLayout } from '../../components/user_layout/UserLayout';
import { ConfirmedOrdersList } from '../../components/orders/user_confirmed_orders/ConfirmedOrdersList';
import userOrdersService from '../../services/userOrders'
import { Context} from '../../context/Context';
import { useContext, useEffect, useState } from 'react';

export const UserOrdersPage = () => {

  const { user, setUser } = useContext(Context);
  const [orders, setOrders] = useState([]);

    const cancelOrder = () => {
        console.log("orden cancelada")
        //usar el service cancel order
        // si no ocurre ningun error entonces puedo eliminar esta orden del front
    }

    useEffect(() => {
      const loggedUserJSON = window.localStorage.getItem("loggedAppUser");
      if (loggedUserJSON) {
        const user = JSON.parse(loggedUserJSON);
        setUser(user);
        userOrdersService.setToken(user)
      }
    }, []);

    useEffect(() => {
      userOrdersService.getByUserId(user.id).then((orders) => setOrders(orders));
    }, []);
    
  return (
    <div className='user-confirmed-orders-wrapper'>
    <UserLayout/>
      <div className='table-content-wrapper'>
        <h2 >Órdenes de Usuario</h2>
        <ConfirmedOrdersList orders={orders}/>
      </div>
    </div>
  );
};


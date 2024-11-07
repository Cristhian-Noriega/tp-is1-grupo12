import './productsSelectionPage.css'
import { UserLayout } from '../../components/user_layout/UserLayout';
import { ConfirmedOrdersList } from '../../components/orders/user_confirmed_orders/ConfirmedOrdersList';

export const UserConfirmedOrdersPage = () => {
    const cancelOrder = () => {
        console.log("orden cancelada")
        //usar el service cancel order
        // si no ocurre ningun error entonces puedo eliminar esta orden del front
    }
  return (
    <div className='user-confirmed-orders-wrapper'>
    <UserLayout/>
      <div className='table-content-wrapper'>
        <h2 >Órdenes de Usuario</h2>
        <ConfirmedOrdersList/>
      </div>
    </div>
  );
};


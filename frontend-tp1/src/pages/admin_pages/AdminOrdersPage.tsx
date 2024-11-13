import "./productsAdministrationPage.css";
import { useEffect } from "react";
import { productsUtils } from "../../utils/productsUtils";
import {userOrdersUtils} from '../../utils/userOrdersUtils'

import userOrdersService from '../../services/userOrders'
import { AdminLayout } from "../../components/admin_layout/AdminLayout";
import { AdminOrdersTable } from "../../components/orders/admin_orders/AdminOrdersTable";

export const AdminOrdersPage = () => {
  const { setUser } = productsUtils();
  const { orders,setOrders, deleteOrder} = userOrdersUtils();

  //Obtiene el token en el localStorgae y trata de obtenerlo de ahi para que el usuario no tenga que volver a iniciar sesion
  useEffect(() => {
    const loggedUserJSON = window.localStorage.getItem("loggedAppUser");
    if (loggedUserJSON) {
      const user = JSON.parse(loggedUserJSON);
      setUser(user);
    }
  }, []);

  useEffect(() => {
    //if (user && user.id) { Por el momento no funciona porque user.id es null
      userOrdersService.getAll().then((orders) => setOrders(orders));
  //  }
  }, []);


  const handleDeleteOrder = (orderId) => {
    console.log("orden cancelada")
    console.log(`order id : ${orderId}`)
    deleteOrder(orderId)
}
  return (
    <div className="product-page-wrapper">
      <AdminLayout/>
      <AdminOrdersTable orders={orders} handleDeleteOrder={handleDeleteOrder}/>
    </div>
  );
};

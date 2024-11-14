import "./productsAdministrationPage.css";
import { useEffect } from "react";
import { productsUtils } from "../../utils/productsUtils";
import {userOrdersUtils} from '../../utils/userOrdersUtils'
import userOrdersService from '../../services/userOrders'
import { AdminLayout } from "../../components/admin_layout/AdminLayout";
import { AdminOrdersTable } from "../../components/orders/admin_orders/AdminOrdersTable";
import { OrderStatusFilter } from "../../components/order_status_filter/OrderStatusFilter";

export const AdminOrdersPage = () => {
  const { setUser } = productsUtils(); // esto manejarlo mejor en el refactor quiza creando un UserContext
  const { orders,setOrders, deleteOrder, setOriginalOrders, getAvailableCommands} = userOrdersUtils();

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
        userOrdersService.getAll().then((orders) => {
          setOriginalOrders(orders);
          setOrders(orders);         
        });
    
  //  }
  }, []);


  const handleDeleteOrder = (orderId) => {
    console.log("orden cancelada")
    console.log(`order id : ${orderId}`)
    deleteOrder(orderId)
}
  return (
    <div className="admin-orders-page-wrapper">
       <div className="admin-orders-body-wrapper">  
      <AdminLayout/>
    
      <div className="admin-orders-body-wrappers">  
        <OrderStatusFilter/>
        <AdminOrdersTable orders={orders} handleDeleteOrder={handleDeleteOrder} getAvailableCommands={getAvailableCommands}/>
        </div>
      </div>
      
    </div>
  );
};

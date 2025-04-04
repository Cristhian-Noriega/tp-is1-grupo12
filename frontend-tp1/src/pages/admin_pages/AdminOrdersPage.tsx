import "./productsAdministrationPage.css";
import { useEffect } from "react";
import { productsUtils } from "../../utils/productsUtils";
import { userOrdersUtils } from "../../utils/userOrdersUtils";
import userOrdersService from "../../services/userOrders";
import { AdminLayout } from "../../components/admin_layout/AdminLayout";
import { AdminOrdersTable } from "../../components/orders/admin_orders/AdminOrdersTable";
import { OrderStatusFilter } from "../../components/order_status_filter/OrderStatusFilter";

export const AdminOrdersPage = () => {
  const { user, getUserFromLocalStorage } = productsUtils(); // esto manejarlo mejor en el refactor quiza creando un UserContext
  const {
    orders,
    setOrders,
    deleteOrder,
    setOriginalOrders,
    getAvailableCommands,
  } = userOrdersUtils();

  //Obtiene el token en el localStorgae y trata de obtenerlo de ahi para que el usuario no tenga que volver a iniciar sesion
  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  useEffect(() => {
    if (user.token) {
      userOrdersService.setToken(user.token);
      userOrdersService.getAll().then((orders) => {
        setOrders(orders);
        setOriginalOrders(orders);
      });
    }
  }, [user]);

  const handleDeleteOrder = (orderId) => {
    console.log("orden cancelada");
    console.log(`order id : ${orderId}`);
    deleteOrder(orderId);
  };
  return (
    <div className="admin-orders-page-wrapper">
      <div className="admin-orders-page-container">
        <AdminLayout />

        <div className="admin-orders-body-wrappers">
          <OrderStatusFilter />
          <AdminOrdersTable
            orders={orders}
            handleDeleteOrder={handleDeleteOrder}
            getAvailableCommands={getAvailableCommands}
          />
        </div>
      </div>
    </div>
  );
};

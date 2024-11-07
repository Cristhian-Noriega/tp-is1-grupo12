import "./productsAdministrationPage.css";
import { useEffect } from "react";
import { productsUtils } from "../../utils/productsUtils";


import { AdminLayout } from "../../components/admin_layout/AdminLayout";
import { AdminOrdersTable } from "../../components/orders/admin_orders/AdminOrdersTable";

export const AdminOrdersPage = () => {
  const { setUser } = productsUtils();

  //Obtiene el token en el localStorgae y trata de obtenerlo de ahi para que el usuario no tenga que volver a iniciar sesion
  useEffect(() => {
    const loggedUserJSON = window.localStorage.getItem("loggedAppUser");
    if (loggedUserJSON) {
      const user = JSON.parse(loggedUserJSON);
      setUser(user);
    }
  }, []);


  return (
    <div className="product-page-wrapper">
      <AdminLayout/>
      <AdminOrdersTable/>
    </div>
  );
};

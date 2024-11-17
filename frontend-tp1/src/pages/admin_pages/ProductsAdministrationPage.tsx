import "./productsAdministrationPage.css";
import { ProductTable } from "../../components/products/product_table/ProductTable";
import { useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import { AdminLayout } from "../../components/admin_layout/AdminLayout";

export const ProductsAdministrationPage = () => {
  const { products, setProducts, user, setUser, getUserFromLocalStorage, handleLogout } = productsUtils();

  //Obtiene el token en el localStorgae y trata de obtenerlo de ahi para que el usuario no tenga que volver a iniciar sesion
  useEffect(() => {
    getUserFromLocalStorage()
  }, []);

  useEffect(() => {
    productsService.getAll().then((products) => setProducts(products));
  }, []);


  return (
    <div className="admin-products-page-wrapper">
      <div className="admin-products-page-container">
      <AdminLayout/>
      <ProductTable products={products} />
      <button
      onClick={handleLogout}>
        Logout
      </button>
      </div>
    </div>
  );
};

import "./productsAdministrationPage.css";
import { useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import { AdminLayout } from "../../components/admin_layout/AdminLayout";
import { AdminProductTable } from "../../components/products/product_table/AdminProductTable";
import { ProductFilter } from "../../components/products_filter/ProductFilter";

export const ProductsAdministrationPage = () => {
  const { products, setProducts,getUserFromLocalStorage } = productsUtils();

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
      <div className='table-content-wrapper'>
        <h2>Todos los Productos</h2>
        <ProductFilter/>
      <AdminProductTable products={products} />
      </div>
      </div>
    </div>
  );
};

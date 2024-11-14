import "./productsSelectionPage.css";
import { useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";


import { UserProductTable } from "../../components/products/product_table/UserProductTable";
import { UserLayout } from "../../components/user_layout/UserLayout";


export const ProductsSelectionPage = () => {
  
  const { products, setProducts, setUser, handleLogout, getUserFromLocalStorage} = productsUtils();

  
  useEffect(() => {
    getUserFromLocalStorage()
  }, []);

  useEffect(() => {
    productsService.getAll().then((products) => setProducts(products));
  }, []);


  return (
    <div className="product-page-wrapper">
      <UserLayout/>
      <UserProductTable products={products} />
      <button
      onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
};

import "./productsSelectionPage.css";
import { useContext, useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import productsAdminService from "../../services/productsAdmin";
import { useNavigate } from "react-router-dom";
import { AdminLayout } from "../../components/admin_layout/AdminLayout";

import { UserProductTable } from "../../components/products/product_table/UserProductTable";


export const ProductsSelectionPage = () => {
  
  const { products, setProducts, user, setUser } = productsUtils();

  
  useEffect(() => {
    const loggedUserJSON = window.localStorage.getItem("loggedAppUser");
    if (loggedUserJSON) {
      const user = JSON.parse(loggedUserJSON);
      setUser(user);
      productsAdminService.setToken(user)
    }
  }, []);

  useEffect(() => {
    productsService.getAll().then((products) => setProducts(products));
  }, []);


  const navigate = useNavigate();

  const handleLogout = () => {
    window.localStorage.clear();
    
    setUser(null);
    navigate('/login');
    };

  return (
    <div className="product-page-wrapper">
      <AdminLayout/>
      <UserProductTable products={products} />
      <button
      onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
};

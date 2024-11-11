import "./productsSelectionPage.css";
import { useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import productsAdminService from "../../services/productsAdmin";
import { useNavigate } from "react-router-dom";

import { UserProductTable } from "../../components/products/product_table/UserProductTable";
import { UserLayout } from "../../components/user_layout/UserLayout";


export const ProductsSelectionPage = () => {
  
  const { products, setProducts, setUser } = productsUtils();

  
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
      <UserLayout/>
      <UserProductTable products={products} />
      <button
      onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
};

import "./productsAdministrationPage.css";
import { NavSideBar } from "../../components/nav_side_bar/admin_nav_side_bar/AdminNavSideBar";
import { ProductTable } from "../../components/products/product_table/ProductTable";
import { useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import productsAdminService from "../../services/productsAdmin";
import { useNavigate } from "react-router-dom";
import { AdminLayout } from "../../components/admin_layout/AdminLayout";

export const ProductsAdministrationPage = () => {
  const { products, setProducts, user, setUser } = productsUtils();

  //Obtiene el token en el localStorgae y trata de obtenerlo de ahi para que el usuario no tenga que volver a iniciar sesion
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
      <ProductTable products={products} />
      <button
      onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
};

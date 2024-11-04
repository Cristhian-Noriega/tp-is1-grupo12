import './homePage.css';
import { NavSideBar } from '../components/nav_side_bar/NavSideBar';
import { ProductTable } from '../components/products/product_table/ProductTable';
import { useEffect } from 'react';
import productsService from "../services/products";
import { productsUtils } from '../utils/productsUtils';
export const HomePage = () => {
  const {products, setProducts,user,setUser} = productsUtils();

  //Obtiene el token en el localStorgae y trata de obtenerlo de ahi para que el usuario no tenga que volver a iniciar sesion
  useEffect(() => {
    const loggedUserJSON = window.localStorage.getItem("loggedAppUser");
    if (loggedUserJSON) {
    const user = JSON.parse(loggedUserJSON);
    setUser(user);
    productsService.setToken(user.token);
    }
  }, []);

  useEffect(() => {
    productsService.getAll().then((products) => setProducts(products));
  }, []);

  return (
    <div className='home-page-wrapper'>
      <NavSideBar />
      <ProductTable products={products}/>
    </div>
  );
}

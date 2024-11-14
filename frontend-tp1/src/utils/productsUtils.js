import { useContext } from "react";
import { Context } from "../context/Context";
import productsAdminService from "../services/productsAdmin";

export const productsUtils = () => {
  const { products, setProducts, user, setUser, handleLogout, getUserFromLocalStorage, showMessage, setShowMessage } = useContext(Context);

  const deleteProduct = async (productId) => {
    try {
      await productsAdminService.remove(productId);
      const updatedProducts = products.filter(
        (product) => product.id !== productId
      );
      setProducts(updatedProducts);
    } catch (exception) {
      console.log("error" + exception.response.data.error);
    }
  };

  const createProduct = async (product) => {
    try {
      console.log(product)
      const productResponse = await productsAdminService.create(
        product
      );
      // setProductos(products.concat(productResponse));
    } catch (exception) {
      console.log("error" + exception.response.data.error);
    }
  };

  return { deleteProduct, createProduct, products, setProducts, user, setUser,handleLogout, getUserFromLocalStorage,showMessage, setShowMessage };
};

import { useContext } from "react";
import { Context } from "../context/Context";
import productsAdminService from "../services/productsAdmin";
import productsService from "../services/products";

export const productsUtils = () => {
  const { products, setProducts, user, setUser, handleLogout, getUserFromLocalStorage, showMessage, setShowMessage } = useContext(Context);

  const deleteProduct = async (productId) => {
    try {
      console.log(`esto eliminadndo el product con id: ${productId}`)
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
      console.log("llegue al productUtils")
      console.log(product)
      const productResponse = await productsAdminService.create(
        product
      );
      setProducts(products.concat(productResponse));

    } catch (exception) {
      console.log("error" + exception.response.data.error);
    }
  };
  const handleGetProductByAttributes = async(attributesPayload) => {
      try{
        const productsWithThisAttributes = await productsService.getByAttributes(
          attributesPayload
        );
        setProducts(productsWithThisAttributes)
        

      } catch(exception) {
        console.log("error" + exception.response.data.error)
      }
  }

  return { deleteProduct, createProduct, products, setProducts, user, setUser,handleLogout, getUserFromLocalStorage,showMessage, setShowMessage, handleGetProductByAttributes };
};

import { useContext } from "react";
import { Context } from "../context/Context";
import productsService from "../services/products";

export const productsUtils = () => {
 
const { products,setProducts,user,setUser} = useContext(Context);

const deleteProduct = async (productId) => {
    try {
      await productsService.remove(productId);
      const updatedProducts = products.filter((product) => product.id !== productId);
      setProducts(updatedProducts);
    } catch (exception) {
      console.log("error" + exception.response.data.error);
    }
  };
  
  return { deleteProduct,products,setProducts,user,setUser};
}

 
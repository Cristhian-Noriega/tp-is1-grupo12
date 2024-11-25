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
        console.log("Respuesta de Product by Attributes")
        console.log(productsWithThisAttributes)
        setProducts(productsWithThisAttributes)
        

      } catch(exception) {
        console.log("error" + exception)
      }
  }
  const handleGetStockByProductId = async(productId) => {
    try{
      console.log("antes de ejecutarmeeee")
      console.log(productId)
      const productStock = await productsAdminService.getStockByProductId(
        productId
      );
      console.log("se hizo la request")
      console.log(productStock)
      return productStock
      

    } catch(exception) {
      console.log("error" + exception.response.data.error)
    }
}
const editProductStock = async(productId, stock) => {
  try{
    console.log("antes de ejecutarmeeee")
    console.log("stock", stock)
    console.log(productId)
    const productStock = await productsAdminService.updateStock(
      productId, {stock}
    );
    console.log("se hizo la request")
    console.log(productStock)
    return productStock
    

  } catch(exception) {
    console.log("error" + exception.response.data.error)
  }
}

  return { deleteProduct, createProduct, products, setProducts, user, setUser,handleLogout, getUserFromLocalStorage,showMessage, setShowMessage, handleGetProductByAttributes, handleGetStockByProductId, editProductStock };
};

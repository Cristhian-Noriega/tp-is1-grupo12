import "./productsSelectionPage.css";
import { useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";


import { UserProductTable } from "../../components/products/product_table/UserProductTable";
import { UserLayout } from "../../components/user_layout/UserLayout";
import CardMessage from "../../components/card_message/CardMessagge";


export const ProductsSelectionPage = () => {
  
  const { products, setProducts, getUserFromLocalStorage, showMessage, setShowMessage} = productsUtils();
  
  
  useEffect(() => {
    getUserFromLocalStorage()
  }, []);

  useEffect(() => {
    productsService.getAll().then((products) => setProducts(products));
  }, []);


  return (
    <div className="product-page-wrapper">
      <UserLayout/>
      <UserProductTable products={products} setProducts={setProducts} />
     
      {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
  );
};

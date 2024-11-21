import "./productsSelectionPage.css";
import { useEffect } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import { UserProductTable } from "../../components/products/product_table/UserProductTable";
import { UserLayout } from "../../components/user_layout/UserLayout";
import CardMessage from "../../components/card_message/CardMessagge";


export const ProductsSelectionPage = () => {
  
  const { products, setProducts, getUserFromLocalStorage, showMessage, setShowMessage, user} = productsUtils();
  
  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  useEffect(() => {
    if (user.token) {
      productsService.setToken(user.token);
      productsService.getAll().then((products) => setProducts(products));
    }
  }, [user]);


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

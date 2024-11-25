import "./productsSelectionPage.css";
import { useEffect, useState } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import { UserProductTable } from "../../components/products/product_table/UserProductTable";
import { UserLayout } from "../../components/user_layout/UserLayout";
import CardMessage from "../../components/card_message/CardMessagge";
import { Button } from "../../components/ui/Button";
import { ProductFilter } from "../../components/products_filter/ProductFilter";


export const ProductsSelectionPage = () => {
  const [isFilterProductsCardVisible, setFilterProductsCardVisible] = useState(false);
  const { products, setProducts, getUserFromLocalStorage, showMessage, setShowMessage, user, handleGetProductByAttributes} = productsUtils();
  
  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  useEffect(() => {
    if (user.token) {
    productsService.setToken(user.token);
    productsService.getAll().then((products) => setProducts(products));
    }
  }, [setProducts,user]);

  const handleShowFilterProductsCard = () => {
    setFilterProductsCardVisible(!isFilterProductsCardVisible);
  };

  const handleCloseCard = () => {
    setFilterProductsCardVisible(false);
  };

  return (
    <div className="product-page-wrapper">
      <UserLayout/>
      <div className='product-page-container'>
        <h2>Seleccion de Productos para armar una orden</h2>
        {isFilterProductsCardVisible && (
              <div className="product-filter-wrapper">
                <span className="close" onClick={handleCloseCard}>
                  &times;
                </span>
                <ProductFilter handleGetProductByAttributes={handleGetProductByAttributes}/>
              </div>
            )}
            
        <div className="buttons-wrapper">
            <Button
              text="Filtrar Productos"
              handleAction={handleShowFilterProductsCard}
              backgroundColor={"var(--primary-color)"}
              backgroundColorHover={"var(--primary-color-hover)"}
            />
          </div>
        <UserProductTable products={products} setProducts={setProducts} />

        {showMessage && (
          <CardMessage
            messageProperties={showMessage}
            onClose={() => setShowMessage(false)} 
          />
        )}
      </div>
    </div>
  );
};

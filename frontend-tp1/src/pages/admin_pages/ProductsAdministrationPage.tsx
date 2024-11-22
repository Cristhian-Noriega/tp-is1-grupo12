import "./productsAdministrationPage.css";
import { useEffect, useState } from "react";
import productsService from "../../services/products";
import productsServiceAdmin from "../../services/productsAdmin";
import { productsUtils } from "../../utils/productsUtils";
import { AdminLayout } from "../../components/admin_layout/AdminLayout";
import { AdminProductTable } from "../../components/products/product_table/AdminProductTable";
import { ProductFilter } from "../../components/products_filter/ProductFilter";
import { Button } from "../../components/ui/Button";
import { OverlayFocus } from "../../components/overlay_focus/OverlayFocus";
import { ProductStockSearch } from "../../components/product_stock_search/ProductStockSearch";

export const ProductsAdministrationPage = () => {
  const { products, setProducts, getUserFromLocalStorage, handleGetProductByAttributes,handleGetStockByProductId, user } = productsUtils();
  const [isFilterProductsCardVisible, setFilterProductsCardVisible] = useState(false);
  const [isSearchProductsCardVisible, setSearchProductsCardVisible] = useState(false);
  useEffect(() => {
    getUserFromLocalStorage();
  }, []);
  
  useEffect(() => {
    if (user.token) {
    productsService.setToken(user.token);
    productsServiceAdmin.setToken(user.token);
    productsService.getAll().then((products) => setProducts(products));
    }
  }, [setProducts,user]);

  const handleShowFilterProductsCard = () => {
    setFilterProductsCardVisible(!isFilterProductsCardVisible);
  };
  
  const handleShowSearchProductsCard = () => {
    setSearchProductsCardVisible(!isSearchProductsCardVisible);
  };

  const handleCloseCard = () => {
    setSearchProductsCardVisible(false);
    setFilterProductsCardVisible(false);
  };

  return (
    <div className="admin-products-page-wrapper">
      {(isFilterProductsCardVisible || isSearchProductsCardVisible) &&  (
        <OverlayFocus handleCloseCard={handleCloseCard} />
      )}
      <div className="admin-products-page-container">
        <AdminLayout />
        <div className="table-content-wrapper">
          <h2>Todos los Productos</h2>

          {isFilterProductsCardVisible && (
            <div className="product-filter-wrapper">
              <span className="close" onClick={handleCloseCard}>
                &times;
              </span>
              <ProductFilter handleGetProductByAttributes={handleGetProductByAttributes}/>
            </div>
          )}
          {isSearchProductsCardVisible && (
            <div className="product-search-by-id-wrapper">
              <span className="close" onClick={handleCloseCard}>
                &times;
              </span>
              <ProductStockSearch handleGetStockByProductId={handleGetStockByProductId}/>
            </div>
          )}
          <div className="buttons-wrapper">
          <Button
            text="Buscar Stock por ID"
            handleAction={handleShowSearchProductsCard}
            backgroundColor={"var(--primary-color)"}
            backgroundColorHover={"var(--primary-color-hover)"}
          />
          <Button
            text="Filtrar Productos"
            handleAction={handleShowFilterProductsCard}
            backgroundColor={"var(--primary-color)"}
            backgroundColorHover={"var(--primary-color-hover)"}
          />
          </div>
          <AdminProductTable products={products} />
        </div>
      </div>
    </div>
  );
};

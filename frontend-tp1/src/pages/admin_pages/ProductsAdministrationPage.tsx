import "./productsAdministrationPage.css";
import { useEffect, useState } from "react";
import productsService from "../../services/products";
import { productsUtils } from "../../utils/productsUtils";
import { AdminLayout } from "../../components/admin_layout/AdminLayout";
import { AdminProductTable } from "../../components/products/product_table/AdminProductTable";
import { ProductFilter } from "../../components/products_filter/ProductFilter";
import { Button } from "../../components/ui/Button";
import { OverlayFocus } from "../../components/overlay_focus/OverlayFocus";

export const ProductsAdministrationPage = () => {
  const { products, setProducts, getUserFromLocalStorage, handleGetProductByAttributes } = productsUtils();
  const [isFilterProductsCardVisible, setFilterProductsCardVisible] = useState(false);

  // Carga el usuario desde el localStorage al cargar la página
  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  // Carga los productos desde el servicio al cargar la página
  useEffect(() => {
    productsService.getAll().then((products) => setProducts(products));
  }, [setProducts]);

  // Alternar visibilidad de la tarjeta de filtros
  const handleShowFilterProductsCard = () => {
    setFilterProductsCardVisible(!isFilterProductsCardVisible);
  };

  // Cerrar la tarjeta de filtros
  const handleCloseCard = () => {
    setFilterProductsCardVisible(false);
  };

  return (
    <div className="admin-products-page-wrapper">
      {isFilterProductsCardVisible && (
        <OverlayFocus handleCloseCard={handleCloseCard} />
      )}
      <div className="admin-products-page-container">
        <AdminLayout />
        <div className="table-content-wrapper">
          <h2>Todos los Productos</h2>
          {isFilterProductsCardVisible && (
            <div className="product-filter-wrapper">
              <ProductFilter handleGetProductByAttributes={handleGetProductByAttributes}/>
            </div>
          )}
          <Button
            text="Filtrar Productos"
            handleAction={handleShowFilterProductsCard}
            backgroundColor={"#FF0"}
            backgroundColorHover={"#F00"}
          />
          <AdminProductTable products={products} />
        </div>
      </div>
    </div>
  );
};

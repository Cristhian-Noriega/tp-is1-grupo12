import { useState } from "react";
import { ProductItem } from "../product_item/ProductItem";
import { ProductTableHeader } from "../product_table_header/ProductTableHeader";
import "./adminProductTable.css";
import ProductDetails from "../product_details/ProductDetails";
import { productsUtils } from "../../../utils/productsUtils";
import CreateProductForm from "../create_product/CreateProductForm";
import { OverlayFocus } from "../../overlay_focus/OverlayFocus";
import { ProductEditStock } from "../../product_edit_stock/ProductEditStock";
const addButton = "/public/assets/add.svg";

export const AdminProductTable = ({ products }) => {
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [isStockEditCardVisible, setStockEditCardVisible] = useState(false);
  const [isDetailsCardVisible, setDetailsCardVisible] = useState(false);
  const [isCreateProductCardVisible, setCreateProductCardVisible] =
    useState(false);

  const { editProductStock, deleteProduct, createProduct } = productsUtils();


  const onShowEditStock= (product) => {
    setStockEditCardVisible(true)
    setSelectedProduct(product);
  }

  const handleShowDetails = (product) => {
    console.log("Producto seleccionado:", product);
    setSelectedProduct(product);
    setDetailsCardVisible(true);
  };

  const handleShowCreateProductForm = () => {
    setCreateProductCardVisible(true);
  };

  const handleCloseCard = () => {
    setDetailsCardVisible(false);
    setCreateProductCardVisible(false);
    setStockEditCardVisible(false)
    setSelectedProduct(null);
  };

  return (
    <div className="product-table-header-wrapper">
      <div className="button-create-product-wrapper">
        <button className="action-button" onClick={handleShowCreateProductForm}>
          <img src={addButton} alt="BOTON EDITAR" className="nav-icon" />
        </button>
      </div>


      <table>
        <ProductTableHeader />
        <tbody>
          {products.map((product) => (
            <ProductItem
              key={product.id}
              product={product}
              onShowEditStock={onShowEditStock}
              deleteProduct={deleteProduct}
              onShowDetails={handleShowDetails}
            />
          ))}
        </tbody>
      </table>
      
      {(isDetailsCardVisible || isCreateProductCardVisible || isStockEditCardVisible) && (
      <OverlayFocus handleCloseCard={handleCloseCard}/>
    )}

      {isStockEditCardVisible && selectedProduct && (
        <div className="card-update-stock-wrapper">
          <span className="close" onClick={handleCloseCard}>
            &times;
          </span>
          <ProductEditStock 
          product={selectedProduct}
          editProductStock={editProductStock}
          handleCloseCard={handleCloseCard}
           />
        </div>
      )}


      {isDetailsCardVisible && selectedProduct && (
        <div className="card-info-wrapper">
          <span className="close" onClick={handleCloseCard}>
            &times;
          </span>
          <ProductDetails product={selectedProduct} />
        </div>
      )}

      {isCreateProductCardVisible && (
        <div className="create-product-card-wrapper">
          <span className="close" onClick={handleCloseCard}>
            &times;
          </span>
          <CreateProductForm createProduct={createProduct}/>
        </div>
      )}
    </div>
  );
};

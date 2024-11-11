import { useState } from "react";
import { ProductItem } from "../product_item/ProductItem";
import { ProductTableHeader } from "../product_table_header/ProductTableHeader";
import "./productTable.css";
import ProductDetails from "../product_details/ProductDetails";
import { productsUtils } from "../../../utils/productsUtils";
import CreateProductForm from "../create_product/CreateProductForm";
const addButton = "/public/assets/add.svg";

export const ProductTable = ({ products }) => {
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [isDetailsCardVisible, setDetailsCardVisible] = useState(false);
  const [isCreateProductCardVisible, setCreateProductCardVisible] =
    useState(false);

  const { deleteProduct, createProduct } = productsUtils();

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
              deleteProduct={deleteProduct}
              onShowDetails={handleShowDetails}
            />
          ))}
        </tbody>
      </table>
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
          <CreateProductForm />
        </div>
      )}
    </div>
  );
};

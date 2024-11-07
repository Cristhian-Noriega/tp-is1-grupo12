import { useState } from "react";
import { ProductTableHeader } from "../product_table_header/ProductTableHeader";
import "./productTable.css";
import ProductDetails from "../product_details/ProductDetails";
import { UserProductItem } from "../product_item/user_product_item/UserProductItem";
const addButton = "/public/assets/add.svg";

export const UserProductTable = ({ products }) => {
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [isDetailsCardVisible, setDetailsCardVisible] = useState(false);


  const handleShowDetails = (product) => {
    console.log("Producto seleccionado:", product);
    setSelectedProduct(product);
    setDetailsCardVisible(true);
  };

  const handleCloseCard = () => {
    setDetailsCardVisible(false);
    setSelectedProduct(null);
  };

  return (
    <div className="product-table-header-wrapper">
      <table>
        <ProductTableHeader />
        <tbody>
          {products.map((product) => (
            <UserProductItem
              key={product.id}
              product={product}
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
    </div>
  );
};

import { useState } from "react";
import { ProductTableHeader } from "../product_table_header/ProductTableHeader";
import "./adminProductTable.css";
import ProductDetails from "../product_details/ProductDetails";
import { UserProductItem } from "../product_item/user_product_item/UserProductItem";


export const UserProductTable = ({ products, setProducts }) => {
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [isDetailsCardVisible, setDetailsCardVisible] = useState(false);


  const handleShowDetails = (product) => {
    setSelectedProduct(product);
    setDetailsCardVisible(true);
  };

  const handleStock = (productId, productNewStock) => {
    console.log('haddlestock')
    console.log(productId, productNewStock)
    setProducts(
      products.map(product =>
        product.id === productId
          ? { ...product, stock: productNewStock }
          : product                               
      )
    );
  }

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
              handleStock={handleStock} 
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

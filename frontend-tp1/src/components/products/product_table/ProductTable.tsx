import { useState } from 'react';
import { ProductItem } from '../product_item/ProductItem';
import { ProductTableHeader } from '../product_table_header/ProductTableHeader'
import './productTable.css'
import ProductDetails from '../product_details/ProductDetails';
import { productsUtils } from '../../../utils/productsUtils';

export const ProductTable = ({products}) => {

  const [selectedProduct, setSelectedProduct] = useState(null);
  const [isCardVisible, setCardVisible] = useState(false);

  const {deleteProduct} = productsUtils();

  const handleShowDetails = (product) => {
    console.log('Producto seleccionado:', product);
    setSelectedProduct(product);
    setCardVisible(true);
  };

  const handleCloseCard = () => {
    setCardVisible(false);
    setSelectedProduct(null);
  };
  
  return (
    <div className='product-table-header-wrapper'>
    <table>
      <ProductTableHeader/>
      <tbody>
      {products.map((product) => (
                        <ProductItem key={product.id} 
                        product={product}
                        deleteProduct={deleteProduct}
                        onShowDetails={handleShowDetails} 
                        />
                    ))}
      </tbody>
    </table>
    {isCardVisible && selectedProduct && (
        <div className='card-info-wrapper'>
            <span className='close' onClick={handleCloseCard}>&times;</span>
            <ProductDetails product={selectedProduct}/>
        </div>
      )}
    </div>
  )
}

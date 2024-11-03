import { useState } from 'react';
import { ProductItem } from '../product_item/ProductItem';
import { ProductTableHeader } from '../product_table_header/ProductTableHeader'
import './productTable.css'
import ProductDetails from '../../info_card/product_details/ProductDetails';
export const ProductTable = () => {
  const products = [
    { 
        id: 1,
        name: 'Producto 1', 
        stock: 20,
        brand: 'Marca PEPE', 
        description: 'Descripción del producto 1',
        state:'confirm',
        extraAttributes: {
            color: 'Rojo',
            weight: '1kg',
        } 
    },
    { 
        id: 2,
        name: 'Producto 2', 
        stock: 0,
        brand: 'Marca B', 
        description: 'Descripción del producto 2',
        state:'canceled', 
        extraAttributes: {
            color: 'Azul',
            weight: '1.5kg',
        } 
    },
];

  const [selectedProduct, setSelectedProduct] = useState(null);
  const [isCardVisible, setCardVisible] = useState(false);

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

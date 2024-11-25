import React from 'react';
import { ProductDetailsCard } from '../../card/ProductDetailsCard';
import ProductDetailsBodyCard from '../../card/body_card/ProductDetailsBodyCard';

const ProductDetails = ({ product }) => {

  return (
    <div className='product-details-wrapper-box'>
      <ProductDetailsCard 
        title='Detalles del Producto'
        body={<ProductDetailsBodyCard product={product} />}    
      />
    </div>
  );
};

export default ProductDetails;

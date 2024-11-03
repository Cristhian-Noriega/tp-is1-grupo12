import React from 'react';
import { Card } from '../card/Card';
import BodyCard from '../card/body_card/BodyCard';

interface ProductDetailsProps {
  product: {
    id: number;
    name: string;
    stock: number;
    brand: string;
    description: string;
    state: string;
    color: string;
    peso: string;
  };
}

const ProductDetails: React.FC<ProductDetailsProps> = ({ product }) => {

  return (
    <div className='product-details-wrapper-box'>
      <Card 
        title='Detalles del Producto'
        body={<BodyCard data={product} />}    
      />
    </div>
  );
};

export default ProductDetails;

import './productDetailsBodyCard.css';

export const ProductDetailsBodyCard = ({ product }) => {
  console.log(product);
  return (
    <div className='body-card'>
      <div className='header-row'>
        <div className='header-item'>Nombre</div>
        <div className='header-item'>Stock</div>
        <div className='header-item'>Marca</div>
        <div className='header-item'>Descripcion</div>
        <div className='header-item'>Atributos Extras</div>
      </div>
      <div className='body-row'>
        {product && (
          <div key={product.id} className='body-item'>
            <div className='item'>{product.name}</div>
            <div className='item'>{product.stock}</div>
            <div className='item'>{product.brand}</div>
            <div className='item'>{product.description}</div>
            <div className='item'>
            {product.extraAttributes || product.extraAtributes ? (
              <div className='extra-attributes'>
                {Object.entries(product.extraAttributes || product.extraAtributes).map(([key, value]) => (
                  <div key={key}>
                    <strong>{key}:</strong> {value}
                  </div>
                ))}
              </div>
            ) : (
              'N/A'
            )}
          </div>

          </div>
        )}
      </div>
    </div>
  );
};

export default ProductDetailsBodyCard;

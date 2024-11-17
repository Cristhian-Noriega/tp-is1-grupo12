import './productDetailsBodyCard.css';

export const ProductDetailsBodyCard = ({ data }) => {
  console.log(data);
  return (
    <div className='body-card'>
      <div className='header-row'>
        <div className='header-item'>Nombre</div>
        <div className='header-item'>Stock</div>
        <div className='header-item'>Marca</div>
        <div className='header-item'>Descripcion</div>
        <div className='header-item'>Estado</div>
        <div className='header-item'>Atributos Extras</div>
      </div>
      <div className='body-row'>
        {data && (
          <div key={data.id} className='body-item'>
            <div className='item'>{data.name}</div>
            <div className='item'>{data.stock}</div>
            <div className='item'>{data.brand}</div>
            <div className='item'>{data.description}</div>
            <div className='item'>{data.state === 'confirm' ? 'Confirmado' : 'Cancelado'}</div>
            <div className='item'>
              {data.extraAttributes && typeof data.extraAttributes === 'object' ? (
                <div className='extra-attributes'>
                  {Object.entries(data.extraAttributes).map(([key, value]) => (
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

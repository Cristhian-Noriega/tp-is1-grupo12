import './bodyCard.css';
import { HeaderBodyCard } from './HeaderBodyCard';

export const BodyCard = ({ itemsFieldDetails,data }) => {
  return (
    <div className='body-card'>
      <div className='header-row'>
        <div className='header-item'>Nombre</div>
        <div className='header-item'>Stock</div>
        <div className='header-item'>Marca</div>
        <div className='header-item'>Descripcion</div>
        <div className='header-item'>Estado</div>
        <div className='header-item'>Peso</div>
        <div className='header-item'>Color</div>
      </div>
      <div className='body-row'>
        {data && 
          <div key={data.id} className='body-item'>
            <div className='item'>{data.name}</div>
            <div className='item'>{data.stock}</div>
            <div className='item'>{data.brand}</div>
            <div className='item'>{data.description}</div>
            <div className='item'>{data.state === 'confirm' ? 'Confirmado' : 'Cancelado'}</div>
            <div className='item'>{data.extraAttributes.weight}</div>
            <div className='item'>{data.extraAttributes.color}</div>
            
          </div>
        }
      </div>
    </div>
  );
}

export default BodyCard;

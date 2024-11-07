import './userProductItem.css';
const addButton = "/public/assets/add.svg";
const deleteButton = "/public/assets/delete.svg";
const infoButton = "/public/assets/info.svg";
import { OrderContext} from '../../../../context/OrderContext';
import { useContext, useState } from 'react';

export const UserProductItem = ({ product, onShowDetails}) => {
  const [quantity, setQuantity] = useState(1);

  const { addToOrder } = useContext(OrderContext);

  const handleAddToOrder = () => {
    console.log(product);  // Verifica que el producto es el esperado
    addToOrder(product);
  };
  

  const incrementQuantity = () => {
    setQuantity(quantity + 1);
  };

  const decrementQuantity = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };


  return (
    <tr>
      <td>{product.name}</td>
      <td>{product.stock}</td>
      <td>{product.brand}</td>
      <td>{product.description}</td>
      <td>{product.state === 'confirm' && 'Confirmado'}
      {product.state === 'processing' && 'En Proceso'}
      {product.state === 'shipped' && 'Enviado'}
      {product.state === 'canceled' && 'Cancelado'}
      </td>
      <td>
        <div className="quantity-control">
          <button onClick={decrementQuantity} className="quantity-button">−</button>
          <span className="quantity-display">{quantity}</span>
          <button onClick={incrementQuantity} className="quantity-button">+</button>
        </div>
        <button className='action-button' onClick={handleAddToOrder}><img src={addButton} alt="BOTON AGREGAR" className="nav-icon" /></button>
        <button className='action-button' onClick={() => onShowDetails(product)}><img src={infoButton} alt="BOTON INFO" className="nav-icon" /></button>
      </td>
    </tr>
  );
}

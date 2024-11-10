import './userProductItem.css';
const addButton = "/public/assets/add.svg";
const infoButton = "/public/assets/info.svg";
import { OrderContext} from '../../../../context/OrderContext';
import { useContext, useState } from 'react';

export const UserProductItem = ({ product, onShowDetails}) => {
  const [productQuantity, setProductQuantity] = useState(1);
  const [productRemaining, setProductRemaining] = useState(product.stock);

  const { addToOrder } = useContext(OrderContext);

  const handleAddToOrder = () => {
    if (productQuantity > productRemaining) {
      alert("No hay suficiente stock disponible.");
      return;
    }

    setProductRemaining(productRemaining - productQuantity)
    console.log(product);  // Verifica que el producto es el esperado
    const productSummary = {
      id: product.id,
      name: product.name,
      quantity: productQuantity,
    };

     
    addToOrder(productSummary);
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
      <div className="product-form-field">
          <label>Agregar producto a mi orden</label>
          <input
            type="number"
            value={productQuantity}
            onChange={(e) => setProductQuantity(Number(e.target.value))}
            className="product-form-input"
          />
        </div>
        <button className='action-button' onClick={handleAddToOrder}><img src={addButton} alt="BOTON AGREGAR" className="nav-icon" /></button>
        <button className='action-button' onClick={() => onShowDetails(product)}><img src={infoButton} alt="BOTON INFO" className="nav-icon" /></button>
      </td>
    </tr>
  );
}
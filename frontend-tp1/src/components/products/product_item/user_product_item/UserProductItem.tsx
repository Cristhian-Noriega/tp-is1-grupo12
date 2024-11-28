import './userProductItem.css';
const addButton = "/public/assets/add.svg";
const infoButton = "/public/assets/info.svg";
import { CurrentOrderContext} from '../../../../context/CurrentOrderContext';
import { useContext, useState } from 'react';
import { Context } from "../../../../context/Context";

export const UserProductItem = ({ product, handleStock, onShowDetails}) => {
  const [productQuantity, setProductQuantity] = useState(1);
  const [productRemaining, setProductRemaining] = useState(product.stock);

  const { setShowMessage } = useContext(Context);
  const { addToOrder } = useContext(CurrentOrderContext);

  const handleAddToOrder = () => {
    if (productQuantity > productRemaining) {
      setShowMessage({
        text: "No hay suficiente stock de este producto",
        type: "error",
        duration: 3000
      })
      return;
    }
    

    setProductRemaining(productRemaining - productQuantity)
    console.log(product); 
    const productSummary = {
      id: product.id,
      name: product.name,
      quantity: productQuantity,
    };
    const productNewStock = productRemaining - productQuantity
    handleStock(product.id, productNewStock)

    setShowMessage({
      text: `Has añadido ${productSummary.quantity} de ${productSummary.name} a tu orden con éxito.`,
      type: "success",
      duration: 3000
    })
    addToOrder(productSummary);
  };

  return (
    <tr>
      <td>{product.name}</td>
      <td>{product.stock}</td>
      <td>{product.brand}</td>
      <td>{product.description}</td>
      <td>
      <div className="product-form-field-container">
          <label>Agregar producto a mi orden</label>
          <div id="stock-input-container">
          <input id="stock-input-container"
            type="number"
            value={productQuantity}
            onChange={(e) => setProductQuantity(Number(e.target.value))}
            className="product-form-input"
            min="0"
          />
          </div>
        </div>
        <button className='action-button' onClick={handleAddToOrder}><img src={addButton} alt="BOTON AGREGAR" className="nav-icon" /></button>
        <button className='action-button' onClick={() => onShowDetails(product)}><img src={infoButton} alt="BOTON INFO" className="nav-icon" /></button>
      </td>
    </tr>
  );
}

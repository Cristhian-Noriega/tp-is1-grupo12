import { useContext } from 'react';
import "./userOrderTable.css"
import { OrderContext} from '../../../context/OrderContext';
const deleteButton = "/public/assets/delete.svg";

export const UserOrdersTable = () => {
    
      const { order, removeProductFromOrder } = useContext(OrderContext);
  return (
    <div className='user-order-table-wrapper'>
        <table>
        <thead>
          <tr>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {order.map(product => (
            <tr key={product.id}>
              <td>{product.name}</td>
              <td>{product.quantity}</td>
              <td>
                <button className='action-button' onClick={() => removeProductFromOrder(product.id)}>
                  <img src={deleteButton} alt="BOTON ELIMINAR" className="nav-icon" />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

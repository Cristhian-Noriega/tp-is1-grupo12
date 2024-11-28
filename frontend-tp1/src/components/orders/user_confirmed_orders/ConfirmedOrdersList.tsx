import { Button } from '../../ui/Button';
import './confirmedOrdersList.css'

export const ConfirmedOrdersList = ({orders, handleCancelOrder}) => {
  
  return (
    <div className='orders-wrapper'>
      {orders.map((order) => (
        <div key={order.id} className='orders-boxes-container'>
          <div className='orders-boxes'>
          <h3>Orden ID: {order.id}</h3>
          <p><strong>Usuario:</strong> {order.userId ? order.userId : "Sin usuario"}</p>
          <p><strong>Estado:</strong> {order.state}</p>
          <p><strong>Hora de Confirmación:</strong> {new Date(order.confirmationTime).toLocaleString()}</p>
          <ul>
          <h4>Productos:</h4>
            {order.items.map((item, itemIndex) => (
              <li className="list-products-container" key={itemIndex}>
                Producto ID: {item.productId}, Nombre: {item.productName}, Cantidad: {item.quantity}
              </li>
            ))}
          </ul>
          </div>
          <div className='button-cancel-container'>
          {order.state == "CONFIRMED" &&
            <Button
              text="Cancelar Orden"
              backgroundColor="#FF6644"
              backgroundColorHover="#FF0000"
              handleAction={() => handleCancelOrder(order.id)}
              />
            }
          </div>
          
        </div>
      ))}
    </div>
  );
};

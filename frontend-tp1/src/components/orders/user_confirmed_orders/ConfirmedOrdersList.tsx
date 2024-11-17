import { Button } from '../../ui/Button';
import './confirmedOrdersList.css'

export const ConfirmedOrdersList = ({orders, handleDeleteOrder}) => {
  
  return (
    <div className='orders-wrapper'>
      {orders.map((order) => (
        <div key={order.id} className='orders-boxes'>
          <h3>Orden ID: {order.id}</h3>
          <p><strong>Usuario:</strong> {order.userId ? order.userId : "Sin usuario"}</p>
          <p><strong>Estado:</strong> {order.state}</p>
          <p><strong>Hora de Confirmación:</strong> {new Date(order.confirmationTime).toLocaleString()}</p>
          <h4>Productos:</h4>
          <ul>
            {order.items.map((item, itemIndex) => (
              <li key={itemIndex}>
                Producto ID: {item.productId}, Nombre: {item.name}, Cantidad: {item.quantity}
              </li>
            ))}
          </ul>
          <Button
            text="Cancelar Orden"
            backgroundColor="#FF6644"
            backgroundColorHover="#FF0000"
            handleAction={() => handleDeleteOrder(order.id)} />
        </div>
      ))}
    </div>
  );
};

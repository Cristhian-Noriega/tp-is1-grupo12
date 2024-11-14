import { Button } from '../../ui/Button';
import './confirmedOrdersList.css'

// Mock de datos de órdenes actualizado
const mockOrders = [
  {
    id: 1,
    userId: "john",
    items: [
      { productId: 102, quantity: 2 },
      { productId: 105, quantity: 1 },
    ],
    state: "CONFIRMED",
    confirmationTime: "2024-11-07T05:09:48.133597",
  },
  {
    id: 2,
    userId: "alice",
    items: [
      { productId: 204, quantity: 3 },
      { productId: 207, quantity: 4 },
    ],
    state: "CONFIRMED",
    confirmationTime: "2024-11-07T15:44:10.135487",
  },
];

export const ConfirmedOrdersList = ({orders}) => {

  
  const cancelOrder = (orderId) => {
    console.log(`Orden con ID ${orderId} cancelada`);
    // Aquí iría la lógica para cancelar la orden usando el servicio
    // Si la cancelación es exitosa, puedes eliminar esta orden del front
  };

  return (
    <div className='orders-wrapper'>
      {mockOrders.map((order) => (
        <div key={order.id} className='orders-boxes'>
          <h3>Orden ID: {order.id}</h3>
          <p><strong>Usuario:</strong> {order.userId ? order.userId : "Sin usuario"}</p>
          <p><strong>Estado:</strong> {order.state}</p>
          <p><strong>Hora de Confirmación:</strong> {new Date(order.confirmationTime).toLocaleString()}</p>
          <h4>Productos:</h4>
          <ul>
            {order.items.map((item, itemIndex) => (
              <li key={itemIndex}>
                Producto ID: {item.productId}, Cantidad: {item.quantity}
              </li>
            ))}
          </ul>
          <Button
            text="Cancelar Orden"
            backgroundColor="#FF6644"
            backgroundColorHover="#FF0000"
            handleAction={() => cancelOrder(order.id)} />
        </div>
      ))}
    </div>
  );
};

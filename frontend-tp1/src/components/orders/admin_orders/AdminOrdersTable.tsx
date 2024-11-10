import { Button } from '../../ui/Button';
import './adminOrdersTable.css';

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

export const AdminOrdersTable = () => {
  const cancelOrder = (orderId) => {
    console.log(`Orden con ID ${orderId} cancelada`);
    // Lógica para cancelar la orden usando el servicio
  };

  return (
    <div className="orders-wrapper">
      <table className="orders-table">
        <thead>
          <tr>
            <th>Orden ID</th>
            <th>Usuario</th>
            <th>Estado</th>
            <th>Hora de Confirmación</th>
            <th>Productos</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {mockOrders.map((order) => (
            <tr key={order.id}>
              <td>{order.id}</td>
              <td>{order.userId ? order.userId : "Sin usuario"}</td>
              <td>{order.state}</td>
              <td>{new Date(order.confirmationTime).toLocaleString()}</td>
              <td>
                <ul>
                  {order.items.map((item, itemIndex) => (
                    <li key={itemIndex}>
                      Producto ID: {item.productId}, Cantidad: {item.quantity}
                    </li>
                  ))}
                </ul>
              </td>
              <td>
                <Button
                  text="Cancelar Orden"
                  backgroundColor="#FF6644"
                  backgroundColorHover="#FF0000"
                  handleAction={() => cancelOrder(order.id)}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

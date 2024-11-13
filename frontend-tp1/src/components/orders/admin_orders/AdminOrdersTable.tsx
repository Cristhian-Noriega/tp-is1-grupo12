import { Button } from '../../ui/Button';
import './adminOrdersTable.css';
export const AdminOrdersTable = ({orders, handleDeleteOrder}) => {


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
          {orders.map((order) => (
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
                  handleAction={() => handleDeleteOrder(order.id)}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

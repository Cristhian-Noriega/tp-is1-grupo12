import { useEffect, useState } from 'react';
import { Button } from '../../ui/Button';
import './adminOrdersTable.css';
export const AdminOrdersTable = ({orders, handleDeleteOrder, getAvailableCommands}) => {
  const [availableCommands, setAvailableCommands] = useState([])

 
  useEffect(() => {
    const showCommands = async () => {
      for (const order of orders) {
      const commands = await getAvailableCommands(order.id);
      setAvailableCommands(commands); 
      }
      
      console.log("COMANDOS AVAILABLE")
      console.log(availableCommands)
    };
    showCommands();
  }, [orders]);
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
                {/* Botón para mostrar comandos */}
                <div>
                  {/* Renderizar los comandos obtenidos para la orden */}
                  {availableCommands.map((command, index) => (
                    <Button 
                    key={index}
                    text = {command.commandName}
                    handleAction={() => console.log(`Comando ${command.commandName} ejecutado`)}
                    backgroundColor="#FF0000"
                    backgroundColorHover="#FFA500"
                    
                    />
                  ))}
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

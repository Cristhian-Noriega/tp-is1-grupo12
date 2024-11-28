import { useEffect, useState } from 'react';
import { Button } from '../../ui/Button';
import { userOrdersUtils } from '../../../utils/userOrdersUtils';
import './adminOrdersTable.css';
import { Context } from '../../../context/Context';
import { useContext } from "react";

export const AdminOrdersTable = ({ orders, getAvailableCommands }) => {
  const [availableCommands, setAvailableCommands] = useState({});  // Cambiamos a un objeto en vez de array

  const { executeCommand, setOriginalOrders, originalOrders, setOrders, deleteOrder } = userOrdersUtils();
  const { setShowMessage, showMessage} = useContext(Context);

  const handleCommandExecutor = (commandName, orderId) => {
    const commandObject = {
      commandName: commandName,
    };
    executeCommand(commandObject, orderId);
    const newState = transformExecuteCommandToAValidState(commandName);
    console.log(newState);
    const updatedOrders = originalOrders.map((order) => {
      if (order.id === orderId) {
        console.log(`Actualizando orden con ID: ${orderId} a estado: ${newState}`);
        return { ...order, state: newState };  // Actualiza la orden con el nuevo estado
      }
      return order;  // Si la orden no es la que corresponde, la devolvemos tal cual esta
    });

    console.log("Órdenes actualizadas:", updatedOrders);

    const newOrders = orders.filter((order) => order.id !== orderId);
    setOrders(newOrders);
    setOriginalOrders(updatedOrders);

    setShowMessage({
      text: `Se aplico el comando ${commandName} a la orden ${orderId}`,
      type: "success",
      duration: 3000
    })
  };

  const transformExecuteCommandToAValidState = (commandName) => {
    console.log("se ejecuta la funcion");
    console.log(commandName);
    switch (commandName) {
      case 'ProcessingOrderCommand':
        return 'PROCESSING';
      case 'SentOrderCommand':
        return 'SENT';
      case 'CancelOrderCommand':
        return 'CANCELED';
      default:
        return 'UNKNOWN';
    }
  };

  const handleDeleteOrder = (orderId) => {
    console.log("ELIMINANDO ORDEN", orderId)
    const confirmed = window.confirm("¿Estás seguro de que deseas cancelar la orden?");
    if (confirmed) {
      deleteOrder(orderId)
    }
    
  }

  useEffect(() => {
    const showCommands = async () => {
      const allCommands = {};  // Usamos un objeto para mapear cada orderId a sus comandos

      for (const order of orders) {
        const commands = await getAvailableCommands(order.id);
        allCommands[order.id] = commands;  // Asocia los comandos con la order.id
      }

      setAvailableCommands(allCommands);  // Actualiza el estado con el objeto de comandos
      console.log("COMANDOS AVAILABLE");
      console.log(allCommands);  // Ahora tendremos un objeto con la estructura { orderId: [comando1, comando2], ... }
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
                <div>
                  {/* Renderizar los comandos obtenidos para la orden correspondiente */}
                  {availableCommands[order.id] && availableCommands[order.id].map((command, commandIndex) => (
                    <Button 
                      key={commandIndex}
                      text={command.commandName}
                      handleAction={() => handleCommandExecutor(command.commandName, order.id)}  // Ahora la acción se asocia con la orden específica
                      backgroundColor="var(--primary-color)"
                      backgroundColorHover="#var(--primary-color-hover)"
                    />
                  ))}
                   <Button 
                      key={commandIndex}
                      text="Eliminar Orden"
                      handleAction={() => handleDeleteOrder(order.id)}  // Ahora la acción se asocia con la orden específica
                      backgroundColor="var(--primary-color)"
                      backgroundColorHover="#var(--primary-color-hover)"
                    />
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

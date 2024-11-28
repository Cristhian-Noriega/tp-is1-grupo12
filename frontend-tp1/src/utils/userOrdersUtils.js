import { useContext } from "react";
import { OrdersContext } from "../context/OrdersContext";
import ordersUserService from "../services/userOrders";
import { Context } from '../context/Context';

export const userOrdersUtils = () => {
  const { orders, setOrders, originalOrders, setOriginalOrders, user } = useContext(OrdersContext);
  const { setShowMessage, showMessage} = useContext(Context);

  const cancelOrder = async (orderId) => {
    try {
      await ordersUserService.cancelOrderByUserId(orderId);
      const updatedOrders = orders.filter(
        (order) => order.id !== orderId
      );
      setOrders(updatedOrders);
      setShowMessage({
        text: `La orden fue cancelada correctamente`,
        type: "success",
        duration: 3000
      })
    } catch (exception) {
      console.log("error" + exception.response.data.error);
      setShowMessage({
        text: `No se puede cancelar una orden que fue creada hace mas de 24 horas`,
        type: "error",
        duration: 3000
      })
    }
  };

  const deleteOrder = async (orderId) => {
    try {
      await ordersUserService.deleteOrder(orderId);
      const updatedOrders = orders.filter(
        (order) => order.id !== orderId
      );
      setOrders(updatedOrders);
      setShowMessage({
        text: `La orden fue eliminada correctamente`,
        type: "success",
        duration: 3000
      })
    } catch (exception) {
      console.log("error" + exception.response.data.error);
      setShowMessage({
        text: `Algo extranio ocurrio! No se pudo eliminar la orden.`,
        type: "error",
        duration: 3000
      })
    }
  };

  const getAvailableCommands = async (orderId) => {
    try{
      const response = await ordersUserService.getAvailableCommands(orderId);
      return response
    
    } catch (exception) {
      console.log("error" + exception.response.data.error);
    }
  }

  const executeCommand = async (command, orderId) => {
    try{
      console.log(`comando ${command}, order id : ${orderId}`) 
      const response = await ordersUserService.executeCommand(command, orderId);
      return response
    
    } catch (exception) {
      console.log("error" + exception.response.data.error);
    }
  }

  return { orders, setOrders,cancelOrder, originalOrders, setOriginalOrders, getAvailableCommands, executeCommand,user, deleteOrder };
};

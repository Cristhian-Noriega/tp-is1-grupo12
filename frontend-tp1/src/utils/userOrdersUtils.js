import { useContext } from "react";
import { OrdersContext } from "../context/OrdersContext";
import ordersUserService from "../services/userOrders";

export const userOrdersUtils = () => {
  const { orders, setOrders, originalOrders, setOriginalOrders } = useContext(OrdersContext);

  const deleteOrder = async (orderId) => {
    try {
      await ordersUserService.deleteOrder(orderId);
      const updatedOrders = orders.filter(
        (order) => order.id !== orderId
      );
      setOrders(updatedOrders);
    } catch (exception) {
      console.log("error" + exception.response.data.error);
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

  return { orders, setOrders,deleteOrder, originalOrders, setOriginalOrders, getAvailableCommands, executeCommand };
};

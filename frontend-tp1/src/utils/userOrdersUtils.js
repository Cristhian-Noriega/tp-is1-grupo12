import { useContext } from "react";
import { OrdersContext } from "../context/OrdersContext";
import ordersUserService from "../services/userOrders";

export const userOrdersUtils = () => {
  const { orders, setOrders } = useContext(OrdersContext);

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

  return { orders, setOrders,deleteOrder };
};

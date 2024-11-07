import {useState} from 'react';
import { OrderContext } from "./OrderContext";

export const OrderProvider = ({ children }) => {
  const [order, setOrder] = useState([]);

  const addToOrder = (productSummary) => {
    console.log("adding order")
    setOrder((prevOrder) => [...prevOrder, productSummary]);
    console.log(productSummary)
    console.log(order)
  };

  const removeProductFromOrder = (productId) => {
    setOrder(order.filter(product => product.id !== productId));
  };

  const clearOrder = () => {
    setOrder([]);
  };

  return (
    <OrderContext.Provider value={{ order, addToOrder, removeProductFromOrder, clearOrder }}>
      {children}
    </OrderContext.Provider>
  );
};

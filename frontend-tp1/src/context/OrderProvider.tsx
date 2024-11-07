import {useState} from 'react';
import { OrderContext } from "./OrderContext";

export const OrderProvider = ({ children }) => {
  const [order, setOrder] = useState([]);

  const addToOrder = (product) => {
    console.log("adding order")
    setOrder((prevOrder) => [...prevOrder, product]);
    console.log(product)
    console.log(order)
  };

  const removeFromOrder = (productId) => {
    setOrder(order.filter(item => item.id !== productId));
  };

  const clearOrder = () => {
    setOrder([]);
  };

  return (
    <OrderContext.Provider value={{ order, addToOrder, removeFromOrder, clearOrder }}>
      {children}
    </OrderContext.Provider>
  );
};

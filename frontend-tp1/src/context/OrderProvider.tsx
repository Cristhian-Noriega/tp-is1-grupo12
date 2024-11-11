import {useState} from 'react';
import { OrderContext } from "./OrderContext";

export const OrderProvider = ({ children }) => {
  const [currentOrder, setCurrentOrder] = useState([]);

  const addToOrder = (productSummary) => {
    console.log("adding order")
    setCurrentOrder((prevOrder) => [...prevOrder, productSummary]);
    console.log(productSummary)
    console.log(currentOrder)
  };

  const removeProductFromOrder = (productId) => {
    setCurrentOrder(currentOrder.filter(product => product.id !== productId));
  };

  const clearOrder = () => {
    setCurrentOrder([]);
  };

  return (
    <OrderContext.Provider value={{ currentOrder,setCurrentOrder, addToOrder, removeProductFromOrder, clearOrder }}>
      {children}
    </OrderContext.Provider>
  );
};

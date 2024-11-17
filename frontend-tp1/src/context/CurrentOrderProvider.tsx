import {useState} from 'react';
import { CurrentOrderContext } from "./CurrentOrderContext";

export const CurrentOrderProvider = ({ children }) => {
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
    <CurrentOrderContext.Provider value={{ currentOrder,setCurrentOrder, addToOrder, removeProductFromOrder, clearOrder }}>
      {children}
    </CurrentOrderContext.Provider>
  );
};

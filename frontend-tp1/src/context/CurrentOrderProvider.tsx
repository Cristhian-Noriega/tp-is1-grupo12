import {useState} from 'react';
import { CurrentOrderContext } from "./CurrentOrderContext";

export const CurrentOrderProvider = ({ children }) => {
  const [currentOrder, setCurrentOrder] = useState([]);

  const addToOrder = (productSummary) => {
    setCurrentOrder((prevOrder) => [...prevOrder, productSummary]); 
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

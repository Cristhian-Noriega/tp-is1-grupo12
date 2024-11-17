import {useState} from 'react';
import { OrdersContext } from "./OrdersContext";

export const OrderProvider = ({ children }) => {
  const [orders, setOrders] = useState([]);
  // Estado para almacenar todas las ordenes originales y poder restaurarlas despues de aplicar filtros
  const [originalOrders, setOriginalOrders] = useState([]); 


  return (
    <OrdersContext.Provider value={{orders, setOrders, originalOrders, setOriginalOrders}}>
      {children}
    </OrdersContext.Provider>
  );
};

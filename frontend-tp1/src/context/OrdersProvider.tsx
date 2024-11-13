import {useState} from 'react';
import { OrdersContext } from "./OrdersContext";

export const OrderProvider = ({ children }) => {
  const [orders, setOrders] = useState([]);


  return (
    <OrdersContext.Provider value={{orders, setOrders}}>
      {children}
    </OrdersContext.Provider>
  );
};

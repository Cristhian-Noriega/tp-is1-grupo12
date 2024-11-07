import { useState } from "react";
import { Context } from "./Context";

export function Provider({ children }) {
    const [user, setUser] = useState("");
    const [products, setProducts] = useState([]);
  
    return (
      <Context.Provider value={{user, setUser, products, setProducts}}>
        {children}
      </Context.Provider>
    );
  }
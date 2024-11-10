import { useState } from "react";
import { Context } from "./Context";

export function Provider({ children }) {
    const [user, setUser] = useState("");
    const [products, setProducts] = useState([]);
  

    const handleAddProduct = (productId) => {
    setProducts(products.filter(product => product.id !== productId));
  };
    return (
      <Context.Provider value={{user, setUser, products, setProducts, handleAddProduct}}>
        {children}
      </Context.Provider>
    );
  }
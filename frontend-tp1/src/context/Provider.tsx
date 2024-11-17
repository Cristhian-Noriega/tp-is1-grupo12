import { useState } from "react";
import { Context } from "./Context";
import loginService from "../services/login";
import { useNavigate } from "react-router-dom";

export function Provider({ children }) {
    const [user, setUser] = useState("");
    const [products, setProducts] = useState([]);
    const [showMessage, setShowMessage] = useState(false);
  
    const navigate = useNavigate();

    const handleAddProduct = (productId) => {
    setProducts(products.filter(product => product.id !== productId));
  };

  const getUserFromLocalStorage = () => {
    const loggedUserJSON = window.localStorage.getItem("loggedAppUser");
    if (loggedUserJSON) {
      const user = JSON.parse(loggedUserJSON);
      setUser(user);
    }
  }
    
  const handleLogout = () => {
    window.localStorage.clear();
    console.log(`USUARIO es nullo? : ${user}`)
    setUser(null);
    navigate('/login');
  }


 
    return (
      <Context.Provider value={{user, setUser, products, setProducts, handleAddProduct, getUserFromLocalStorage, handleLogout, showMessage, setShowMessage}}>
        {children}
      </Context.Provider>
    );
  }
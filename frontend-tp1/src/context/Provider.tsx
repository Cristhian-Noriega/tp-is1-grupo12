import { useState } from "react";
import { Context } from "./Context";

export function Provider({ children }) {
    const [contactos, setContactos] = useState([]);
    const [message, setMessage] = useState(null);
    const [user, setUser] = useState(null);
  
    return (
      <Context.Provider value={{ message, setMessage, user, setUser, contactos, setContactos }}>
        {children}
      </Context.Provider>
    );
  }
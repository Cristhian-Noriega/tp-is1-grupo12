import { useContext } from "react";
import loginService from "../services/login";
import contactoService from "../services/contactos";
import { Context } from "../context/Context";
import { useNavigate } from 'react-router-dom';

export const loginUtils = () => {
  
const { user,setUser, setMessage}  = useContext(Context);

const handleLogin = async (username, password) => {

    try {
        const user = await loginService.login({
        username,
        password,
        });
        window.localStorage.setItem("loggedContactoappUser", JSON.stringify(user)); // Guarda el token en el localstorage
        contactoService.setToken(user.token); //Setea la variable token con el user token
        setUser(user); //Setea el user como el user que recien se logueo que contendra campos de informacion que definimos en nuestro backend
    } catch (exception) {
        setMessage("error" + exception.response.data.error);
    }
    };



    return { user,setUser, handleLogin };
}
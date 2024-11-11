import { useContext } from "react";
import loginService from "../services/login";
import { Context } from "../context/Context";
import productsAdminService from "../services/productsAdmin";

export const loginUtils = () => {
  
const { user,setUser}  = useContext(Context);

const handleLogin = async (email, password) => {

    try {
        const user = await loginService.login({
        email,
        password,
        });
        console.log("ESTE ES UN USUARIO")
        console.log(user)
        window.localStorage.setItem("loggedUser", JSON.stringify(user)); // Guarda el token en el localstorage
        productsAdminService.setToken(user); 
        setUser(user); //Setea el user como el user que recien se logueo que contendra campos de informacion que definimos en nuestro backend
    } catch (exception) {
        console.log("error" + exception.response.data.error);
    }
    };



    return { user,setUser, handleLogin };
}
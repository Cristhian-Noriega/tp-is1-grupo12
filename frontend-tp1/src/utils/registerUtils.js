
import registerService from "../services/register";
import { useContext } from "react";
import { Context } from '../context/Context';


export const registerUtils = () => {
    const randomImageUrl = 'https://picsum.photos/200/300' 
    const { setShowMessage, showMessage} = useContext(Context);
    
const handleRegister = async (name,surname,email, password,gender,age,address) => {

try {
    const userRegistrationData = {
        name: name,
        surname: surname,
        email: email,
        password: password,
        gender: gender,
        age: age,
        address: address
    }
    console.log(userRegistrationData)
    const user = await registerService.register(userRegistrationData);
    setShowMessage({
        text: `Se registro correctamente el usuario.`,
        type: "success",
        duration: 3000
      })
    
    } catch (exception) {
        console.log("error" + exception.response.data.error);
        setShowMessage({
            text: `Error al crear el usuario.`,
            type: "error",
            duration: 3000
          })
    }
    };



    return { handleRegister };
}
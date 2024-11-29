
import { useContext } from "react";
import userService from "../services/user";
import { Context } from "../context/Context";

export const validationEmailUtils = () => {
  
const {setShowMessage}  = useContext(Context);
const handleValidateEmail = async (email) => {
    console.log(email)
    try{
        await userService.validateEmail(email)
        setShowMessage({
        text: `El email: ${email} ha sido verificado Correctamente. Ya puede cerrar esta pagina.`,
        type: "success",
        duration: 3000
      })
    }catch{
      setShowMessage({
        text: `El email no se pudo verificar. Pruebe con otro`,
        type: "error",
        duration: 3000
      })
    }
}

return { handleValidateEmail};

}
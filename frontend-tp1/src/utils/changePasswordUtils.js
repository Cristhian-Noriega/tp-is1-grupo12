import { useContext } from "react";
import changePasswordService from "../services/changePassword";
import { Context } from "../context/Context";

export const changePasswordUtils = () => {
  
const { setShowMessage}  = useContext(Context);
const handlePasswordChange = async (email,newPassword) => {
    try {
        const user = await changePasswordService.changePassword({
            email,
            newPassword
        });
        setShowMessage({
            text: `Se cambio correctamente la contraseña de ${email}.`,
            type: "success",
            duration: 3000
          })
    
    } catch (exception) {
        console.log("error" + exception.response.data.error);
        setShowMessage({
            text: `Ocurrio un error al cambiar la contraseña.`,
            type: "error",
            duration: 3000
          })
    }
    };



    return { handlePasswordChange };
}
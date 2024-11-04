
import changePasswordService from "../services/changePassword";

export const changePasswordUtils = () => {
  
    
const handlePasswordChange = async (email,newPassword) => {
    try {
        const user = await changePasswordService.changePassword({
            email,
            newPassword
        });
    
    } catch (exception) {
        console.log("error" + exception.response.data.error);
    }
    };



    return { handlePasswordChange };
}
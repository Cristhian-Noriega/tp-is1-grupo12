    
import requestPassChangeService from "../services/requestPassChange";



export const requestPassChangeUtils = () => {
  
    
const handleSendEmailToChangePassword = async (email) => {
    try {
        const user = await requestPassChangeService.sendEmailToChangePassword(email);
    
    } catch (exception) {
        console.log("error" + exception.response.data.error);
    }
    };



    return { handleSendEmailToChangePassword  };
}
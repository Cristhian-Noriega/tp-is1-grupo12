
import registerService from "../services/register";



export const registerUtils = () => {
  
    
const handleRegister = async (name,surname,email, password,gender,age,address) => {
const randomImageUrl = 'https://picsum.photos/200/300'
    try {
        const user = await registerService.register({
        name,
        surname,
        email,
        password,
        gender,
        age,
        address,
        randomImageUrl
        
        });
    
    } catch (exception) {
        console.log("error" + exception.response.data.error);
    }
    };



    return { handleRegister };
}
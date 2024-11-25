
import registerService from "../services/register";



export const registerUtils = () => {
  
    
const handleRegister = async (name,surname,email, password,gender,age,address) => {
const randomImageUrl = 'https://picsum.photos/200/300'
    
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
    console.log("Usuario que se registrara")
    console.log(userRegistrationData)
        const user = await registerService.register(userRegistrationData);
    
    } catch (exception) {
        console.log("error" + exception.response.data.error);
    }
    };



    return { handleRegister };
}
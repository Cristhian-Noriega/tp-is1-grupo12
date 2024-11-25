const baseUrl = 'http://localhost:8080/users/publicProfile';
import axios from 'axios';

let token = null;

const setToken =(newToken) => {
    token = newToken 
  } 


const getPublicProfile = async () => {
    try {
        const response = await axios.get(baseUrl, { headers: { "Authorization": `Bearer ${token}` } });
        return response.data;
    } catch (error) {
        console.error('Error en la petición:', error);
    }
};

export default { getPublicProfile, setToken };
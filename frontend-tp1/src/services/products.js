import axios from "axios";
const baseUrl = "http://localhost:8080/products";
let token = null;

const setToken =(newToken) => {
  token = newToken 
} 

const getAll = async () => {
  const response = await axios.get(baseUrl, 
    { headers: { "Authorization": `Bearer ${token}` } });
  return response.data;
};

const getByAttributes = async (attributes) => {
  console.log("service getByAttributes")
  console.log(attributes)
  console.log("tokoeko",token)
<<<<<<< HEAD
  const response = await axios.request({
    method: 'GET',
    url: `${baseUrl}/search`,
    headers: { 
      Authorization: `Bearer ${token}` // Token de autorización
    },
    data: attributes // Aquí se envía el cuerpo de la solicitud
=======
  const response = await axios.get(`${baseUrl}/search`, {
    params: attributes, 
    headers: { 
      Authorization:`Bearer ${token}`
    }
>>>>>>> acf73a0ab0045c1a49cf92324465851831dcea97
  });
  console.log("dataa respuesta")
  console.log(response)
  return response.data
} 

export default {
  getAll, getByAttributes, setToken
};
